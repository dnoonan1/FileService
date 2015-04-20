package util.file.format;

import java.text.ParseException;
import util.file.FileFormat;
import util.LinkedRecord;
import util.Record;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dan
 */
public class CsvFileFormat implements FileFormat {

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

    public CsvFileFormat() {
        this(COMMA, NO_HEADER);
    }
    
    public CsvFileFormat(boolean hasHeader) {
        this(COMMA, hasHeader);
    }
    
    public CsvFileFormat(char separator) {
        this(separator, NO_HEADER);
    }
    
    public CsvFileFormat(char separator, boolean hasHeader) {
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
    public final List<Record> decode(String data) throws ParseException {
        List<Record> records = new ArrayList<>();
        Record record;
        ArrayList<String> headers;
        String[] lines = data.split(NEWLINE);
        int i = 0;
        if (lines.length > 0) {
            if (lines.length > 0 && hasHeader) {
                headers = parseHeaders(lines[0]);
                i++;
            } else {
                headers = new ArrayList<>();
            }
            for (; i < lines.length; i++) {                
                record = parseLine(lines[i], headers);               
                records.add(record);
            }
        }
        return records;
    }
    
    private ArrayList<String> parseHeaders(String line) throws ParseException {
        ArrayList<String> headers = new ArrayList<>();
        String value;
        char sep = separator.charAt(0);
        int begin = 0;
        int i = 0;
        while (i < line.length()) {
            begin = i;
            if (line.charAt(i) == QUOTE_CHAR) {
                i++;
                while (i < line.length()) {
                    if (line.charAt(i) == QUOTE_CHAR) {
                        if (i + 1 < line.length()) {
                            if (line.charAt(i+1) == sep) {
                                break;    
                            }  else if (line.charAt(i+1) == QUOTE_CHAR) {
                                i += 2;
                                continue;
                            }
                            throw new ParseException("", i);
                        } else {
                            break;
                        }
                    }
                    i++;
                }
                value = line.substring(begin + 1, i)
                        .replace(ESCAPED_QUOTE, QUOTE);
            } else {
                while (i < line.length() && line.charAt(i) != sep) {
                    i++;
                }
                value = line.substring(begin, i);
            }
            headers.add(value);
            i+= 2;
        }
        return headers;
    }
    
    private Record parseLine(String line, ArrayList<String> headers)
            throws ParseException {
        Record record = new LinkedRecord();
        int valueCount = 0;
        String value;
        char sep = separator.charAt(0);
        int begin = 0;
        int i = 0;
        while (i < line.length()) {
            begin = i;
            if (line.charAt(i) == QUOTE_CHAR) {
                i++;
                while (i < line.length()) {
                    if (line.charAt(i) == QUOTE_CHAR) {
                        if (i + 1 < line.length()) {
                            if (line.charAt(i+1) == sep) {
                                break;    
                            } else if (line.charAt(i+1) == QUOTE_CHAR) {
                                i += 2;
                                continue;
                            }
                            throw new ParseException("", i);
                        } else {
                            break;
                        }
                    }
                    i++;
                }
                value = line.substring(begin + 1, i)
                        .replace(ESCAPED_QUOTE, QUOTE);
            } else {
                while (i < line.length() && line.charAt(i) != sep) {
                    i++;
                }
                value = line.substring(begin, i);
            }
            if (valueCount >= headers.size()) {
                headers.add("[" + valueCount + "]");
            }
            record.put(headers.get(valueCount), value);
            valueCount++;
            i++;
        }
        return record;
    }
    
}
