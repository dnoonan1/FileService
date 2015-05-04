package edu.wctc.advjava.drn.service.file.format;

import edu.wctc.advjava.drn.service.file.RecordFileFormat;
import edu.wctc.advjava.drn.util.*;
import java.util.*;

/**
 * This class represents the CSV (Comma Separated Values or <i>Character</i> 
 * Separated Values) file format. The CSV file format consists of any number of
 * records, with one record per line. Each line has the following format:
 * <blockquote>
 * <i>data value 1</i>,<i>data value 2</i>,...<i>data value</i> n
 * </blockquote>
 * Most commonly, the comma - called the <i>separator</i> character - is used to
 * separate data values. However, other characters may be used instead (see the
 * constructors).
 * 
 * @author Dan Noonan
 * @see RecordFileFormat
 * @see Record
 * @see LineParser
 */
public class CSVFileFormat
        implements RecordFileFormat, LineParser<Record> {

    private static final boolean HAS_HEADER = true;
    private static final char COMMA = ',';
    private static final String ESCAPED_QUOTE = "\"\"";
    
    private char separator;
    private boolean hasHeader;
    private List<String> headers;

    /**
     * Constructs a new instance of the {@code CSVFileFormat} which uses the 
     * comma character (,) as a separator and reads the first line as a list of
     * column headers by the {@code decode} method.
     */
    public CSVFileFormat() {
        this(COMMA, HAS_HEADER);
    }
    
    /**
     * Constructs a new instance of the {@code CSVFileFormat} which uses the 
     * comma character (,) as a separator. If {@code hasHeader} is {@code true},
     * the first line is read as a list of column headers by the {@code decode}
     * method. Otherwise, if {@code hasHeader} is {@code false}, the first line
     * is the first record. The headers are taken either from those set by one 
     * of the {@code setHeaders} methods, or are auto-generated (see the 
     * {@code decode} method to see how this is done).
     * 
     * @param hasHeader whether to read the first line as a header 
     */
    public CSVFileFormat(final boolean hasHeader) {
        this(COMMA, hasHeader);
    }
    
    /**
     * Constructs a new instance of the {@code CSVFileFormat} which uses the 
     * specified character as a separator and reads the first line as a list of
     * column headers by the {@code decode} method.
     * 
     * @param separator the character to use as a separator 
     */
    public CSVFileFormat(final char separator) {
        this(separator, HAS_HEADER);
    }
    
    /**
     * Constructs a new instance of the {@code CSVFileFormat} which uses the 
     * specified character as a separator. If {@code hasHeader} is {@code true},
     * the first line is read as a list of column headers by the {@code decode}
     * method. Otherwise, if {@code hasHeader} is {@code false}, the first line
     * is the first record. The headers are taken either from those set by one 
     * of the {@code setHeaders} methods, or are auto-generated (see the 
     * {@code decode} method to see how this is done).
     * 
     * @param separator the character to use as a separator
     * @param hasHeader whether to read the first line as a header
     */
    public CSVFileFormat(final char separator, final boolean hasHeader) {
        setSeparator(separator);
        this.hasHeader = hasHeader; // no validation required (boolean)
        // this.headers = null; // (default)
    }

    // Used only in constructor - private
    private void setSeparator(final char separator) {
        if (separator == Char.CR || separator == Char.LF
                || separator == Char.QUOTE) {
            throw new IllegalArgumentException();
        }
        this.separator = separator;
    }
    
    /**
     * Sets the column headers used when decoding CSV-formatted text.
     * 
     * @param headers the column headers (array of Strings)
     */
    public final void setHeaders(final String[] headers) {
        this.headers = Arrays.asList(headers);
    }
    
    /**
     * Sets the column headers used when decoding CSV-formatted text.
     * 
     * @param headers the column headers (List of Strings)
     */
    public final void setHeaders(final List<String> headers) {
        this.headers = headers;
    }
    
    /**
     * Encodes the given {@code List} of {@code Record}s as a {@code String} of
     * CSV-formatted text.
     * 
     * @param records the List of Records to encode
     * @return the Records, formatted as CSV
     */
    @Override
    public final String encode(final List<Record> records) {
        int lastOccurence;
        final StringBuilder data = new StringBuilder();
        final String separatorString = Character.toString(separator);
        if (hasHeader) {
            final Record last = records.get(records.size() - 1);
            final Set<String> keys = last.keySet();
            for (String key : keys) {
                data.append(key).append(separator);
            }
            data.deleteCharAt(data.length() - 1);
        }
        for (Record record : records) {
            for (String s : record.values()) {
                if (s.contains(QUOTE)
                        || s.contains(separatorString)) {
                    s = s.replaceAll(QUOTE, ESCAPED_QUOTE);
                    data.append(QUOTE)
                        .append(s)
                        .append(QUOTE);
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

    /**
     * Decodes the given String of CSV-formatted data into a {@code List} of
     * {@code Record}s.
     * 
     * @param data the CSV-formatted data to decode
     * @return a List of Records
     * @throws CSVParseException if the given data does not follow the CSV 
     *      format 
     */
    @Override
    public final List<Record> decode(final String data)
            throws CSVParseException {
        final List<Record> records = new ArrayList<>();
        Record record;
        final String[] lines = data.split(CR);
        int i = 0;
        if (lines.length > 0) {
            if (hasHeader) {
                try {
                    headers = parseList(lines[i++]);
                } catch (CSVParseException e) {
                    e.setLineNumber(i + 1);
                    throw e;
                }
            } else if (headers == null) {
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
    
    /**
     * Parses a line of CSV-formatted text, and returns the data as a 
     * {@code Record}. This method is used by {@code decode()} (see above) but
     * is made public so that other classes and {@code FileFormats}/parsers 
     * may make use of it.
     * 
     * @param line the line to parse
     * @return the Record represented by the line of data
     * @throws CSVParseException if the line does not follow the CSV format
     */
    @Override
    public final Record parseLine(final String line) 
            throws CSVParseException {
        
        final Record record = new LinkedRecord();
        final List<String> values = parseList(line);
        for (int i = 0; i < values.size(); i++) {
            if (i >= headers.size()) {
                headers.add("[" + i + "]");
            }
            record.put(headers.get(i), values.get(i));
            i++;
        }
        return record;
    }
    
    /**
     * Parses a line of CSV-formatted text, and returns the data as a 
     * {@code List} of {@code Strings}s. This method is used by {@code decode()} 
     * (see above) but is made public so that other classes and 
     * {@code FileFormats}/parsers may make use of it.
     * 
     * @param line the line to parse
     * @return the list of data-entries represented by the line of data
     * @throws CSVParseException if the line does not follow the CSV format
     */
    public final List<String> parseList(final String line)
            throws CSVParseException {
        
        final List<String> values = new ArrayList<>();
        String value;
        char nextChar;
        
        int i0; // starting index of the current sequence
        int i = 0; // current char index
        while (i < line.length()) {            
            i0 = i; // set the start of the sequence to the current index           
            // If the sequence begins with " ...
            if (line.charAt(i) == Char.QUOTE) {                
                i++; // ... move on to the next char               
                // and search for the closing ".
                while (i < line.length()) {                    
                    // If there is another " ...
                    if (line.charAt(i) == Char.QUOTE) {                        
                        // ... check the char after it (if it exists).
                        if (i + 1 < line.length()) {
                            nextChar = line.charAt(i+1);
                            // If the next char is the separator ...
                            if (nextChar == separator) {
                                // ... move to the next char and we're 
                                break; // at the end of the sequence!    
                            }  else if (nextChar == Char.QUOTE) {
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
                value = line.substring(i0 + 1, i).replace(ESCAPED_QUOTE, QUOTE);
                i++;
            } else {                
                // Otherwise, if the sequence did not begin with a quote ("),
                // search until the separator char.
                while (i < line.length() && line.charAt(i) != separator) {
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

    /**
     * Gets the hash code for this {@code CSVFileFormat}.
     * 
     * @return the hash code for this object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.separator;
        hash = 37 * hash + (this.hasHeader ? 1 : 0);
        return hash;
    }

    /**
     * Compares this {@code CSVFileFormat} with the specified {@code Object} for
     * equality. The result is true if and only if the {@code Object} is a 
     * {@code CSVFileFormat} equivalent to this {@code CSVFileFormat}.
     * 
     * @param obj the object to be compared with
     * @return true if the given Object is a CSVFileFormat equivalent to this 
     *     CSVFileFormat, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CSVFileFormat) {
            CSVFileFormat that = (CSVFileFormat)obj;
            return this.separator == that.separator
                && this.hasHeader == that.hasHeader;
        }
        return false;
    }

    /**
     * Returns a {@code String} representation of this {@code CSVFileFormat}.
     * 
     * @return a String representation of this object 
     */
    @Override
    public String toString() {
        return "CSVFileFormat{" + "separator=" + separator + ", hasHeader=" + hasHeader + ", headers=" + headers + '}';
    }
    
}
