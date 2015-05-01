package edu.wctc.advjava.drn.service.file.format;

import java.text.ParseException;
import edu.wctc.advjava.drn.service.file.FileFormat;
import edu.wctc.advjava.drn.util.LinkedRecord;
import edu.wctc.advjava.drn.util.Record;
import java.util.ArrayList;
import java.util.List;
import edu.wctc.advjava.drn.util.LineParser;

/**
 *
 * @author Dan
 */
public class CSVFileFormat implements FileFormat, LineParser<List<String>> {

    private static final char CR = '\r';
    private static final char LF = '\n';
    private static final boolean NO_HEADER = false;
    private static final String NEWLINE = String.format("%n");
    private static final char COMMA = ',';
    private static final String QUOTE = "\"";
    private static final String ESCAPED_QUOTE = "\"\"";
    private static final char QUOTE_CHAR = '\"';
    
    private String separator;
    private boolean hasHeader;

    public CSVFileFormat() {
        this(COMMA, NO_HEADER);
    }
    
    public CSVFileFormat(boolean hasHeader) {
        this(COMMA, hasHeader);
    }
    
    public CSVFileFormat(char separator) {
        this(separator, NO_HEADER);
    }
    
    public CSVFileFormat(char separator, boolean hasHeader) {
        setSeparator(separator);
        this.hasHeader = hasHeader; // no validation required (boolean)
    }

    public final void setSeparator(char separator) {
        if (separator == CR || separator == LF || separator == QUOTE_CHAR) {
            throw new IllegalArgumentException();
        }
        this.separator = Character.toString(separator);
    }
    
    public final boolean hasHeader() {
        return hasHeader;
    }
    
    public final void setHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }
    
    @Override
    public final String encode(List<Record> records) {
        int lastOccurence;
        StringBuilder data = new StringBuilder();
        for (Record record : records) {
            for (String s : record.values()) {
                if (s.contains(QUOTE) || s.contains(separator)) {
                    s = s.replaceAll(QUOTE, ESCAPED_QUOTE);
                    data.append(QUOTE).append(s).append(QUOTE);
                } else {
                    data.append(s);
                }
                data.append(separator);
            }
            lastOccurence = data.lastIndexOf(separator);
            data.delete(lastOccurence, lastOccurence + separator.length());
            data.append(NEWLINE);
        }
        lastOccurence = data.lastIndexOf(NEWLINE);
        data.delete(lastOccurence, lastOccurence + NEWLINE.length());
        return data.toString();
    }

    @Override
    public final List<Record> decode(String data) throws CSVParseException {
        List<Record> records = new ArrayList<>();
        Record record;
        List<String> headers;
        String[] lines = data.split(NEWLINE);
        List<String> values;
        int i = 0;
        if (lines.length > 0) {
            if (hasHeader) {
                try {
                    headers = parseLine(lines[i++]);
                } catch (CSVParseException e) {
                    e.setLineNumber(i+1);
                    throw e;
                }
            } else {
                headers = new ArrayList<>();
            }
            for (; i < lines.length; i++) {
                record = new LinkedRecord();
                try {
                    values = parseLine(lines[i]);                
                    for (int j = 0; j < values.size(); j++) {
                        if (j >= headers.size()) {
                            headers.add("[" + j + "]");
                        }
                        record.put(headers.get(j), values.get(j));
                    }
                    records.add(record);
                } catch (CSVParseException e) {
                    e.setLineNumber(i+1);
                    throw e;
                }
            }
        }
        return records;
    }
    
    // This method is public so that other FileFormats and parsers can
    // potentially use it. It may be moved to a separate class in the future.
    @Override
    public List<String> parseLine(String line) throws CSVParseException {
        
        List<String> values = new ArrayList<>();
        String value;
        char sep = separator.charAt(0);
        char nextChar;
        
        int i0; // starting index of the current sequence
        int i = 0; // current char index
        while (i < line.length()) {            
            i0 = i; // set the start of the sequence to the current index           
            // If the sequence begins with " ...
            if (line.charAt(i) == QUOTE_CHAR) {                
                i++; // ... move on to the next char               
                // and search for the closing ".
                while (i < line.length()) {                    
                    // If there is another " ...
                    if (line.charAt(i) == QUOTE_CHAR) {                        
                        // ... check the char after it (if it exists).
                        if (i + 1 < line.length()) {
                            nextChar = line.charAt(i+1);
                            // If the next char is the separator ...
                            if (nextChar == sep) {
                                // ... move to the next char and we're 
                                break; // at the end of the sequence!    
                            }  else if (nextChar == QUOTE_CHAR) {
                                // Otherwise, if the next char is "
                                i += 2; // skip the next char
                                continue; // and continue searching for the 
                                          // closing ".
                            } else {
                                // If the previous " is not followed by either
                                // the separator or another ", the line is 
                                // formatted incorrectly.
                                throw new CSVParseException(
                                    "\" or " + separator + " expected, "
                                  + nextChar + " found instead.\n"
                                  + line.substring(0, i + 1) + " <-- error",
                                    i + 1
                                );
                            }
                        } else {
                            // If there isn't another char, this is the last
                            // sequence of the line, and we're done!
                            break;
                        }
                    }
                    // If nothing was found above, move on to the next char.
                    i++;
                }                
                // Get the current value as a substring of everything between
                // the opening and closing quotes. Replace "" with ".
                value = line.substring(i0 + 1, i)
                        .replace(ESCAPED_QUOTE, QUOTE);
                i++;
            } else {                
                // Otherwise, if the sequence did not begin with a quote ("),
                // search until the separator char.
                while (i < line.length() && line.charAt(i) != sep) {
                    i++;
                }
                // Get the current value as a substring from the beginning of
                // the sequence to the separator char (exclusive).
                value = line.substring(i0, i);                
            }
            // Add the last-found value to the list of values. 
            values.add(value);
            i++;
        }
        // Return the list of values.
        return values;
    }
    
}
