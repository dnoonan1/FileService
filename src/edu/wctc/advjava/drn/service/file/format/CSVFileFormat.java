package edu.wctc.advjava.drn.service.file.format;

import edu.wctc.advjava.drn.service.file.RecordFileFormat;
import edu.wctc.advjava.drn.util.*;
import java.util.*;

/**
 *
 * @author Dan
 */
public class CSVFileFormat implements RecordFileFormat, LineParser<Record> {

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
    private List<String> headers;

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
        // want this.headers = null;
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
    
    public final void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }
    
    public final void setHeaders(String[] headers) {
        this.headers = Arrays.asList(headers);
    }
    
    public final void setHeaders(List<String> headers) {
        this.headers = headers;
    }
    
    @Override
    public final String encode(List<Record> records) {
        int lastOccurence;
        StringBuilder data = new StringBuilder();
        if (hasHeader) {
            Record last = records.get(records.size() - 1);
            Set<String> keys = last.keySet();
            for (String key : keys) {
                data.append(key).append(separator);
            }
            data.deleteCharAt(data.length() - 1);
        }
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
            data.deleteCharAt(data.length() - 1);
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
        String[] lines = data.split(NEWLINE);
        List<String> values;
        int i = 0;
        if (lines.length > 0) {
            if (hasHeader) {
                try {
                    headers = parseList(lines[i++]);
                } catch (CSVParseException e) {
                    e.setLineNumber(i+1);
                    throw e;
                }
            } else {
                headers = new ArrayList<>();
            }
            for (; i < lines.length; i++) {
                try {
                    record = parseLine(lines[i]);                
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
    public Record parseLine(String line) throws CSVParseException {
        
        Record record = new LinkedRecord();
        List<String> values = parseList(line);
        for (int i = 0; i < values.size(); i++) {
            if (i >= headers.size()) {
                headers.add("[" + i + "]");
            }
            // Add the last-found value to the list of values.
            record.put(headers.get(i), values.get(i));
            i++;
        }
        // Return the list of values.
        return record;
    }
    
    public List<String> parseList(String line) throws CSVParseException {
        
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
                                  + nextChar + " found instead\n"
                                  + line.substring(0, i + 1) + "<-- error",
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
