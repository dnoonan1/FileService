package util.file.format;

import util.file.FileFormat;
import util.file.LinkedRecord;
import util.file.Record;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public final void setHeader(boolean header) {
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
    public final List<Record> decode(String data) {
        List<Record> records = new ArrayList<>();
        Record record;
        List<String> headers;
        String[] lines = data.split(NEWLINE);
        int i = 0;
        if (lines.length > 0) { // may not need this
            if (hasHeader) {
                headers = new ArrayList(
                        Arrays.asList(lines[0].split(separator))
                );
                i++;
            } else {
                headers = new ArrayList<>();
            }
            for (; i < lines.length; i++) {
                record = new LinkedRecord();
                String[] cols = lines[i].split(separator);
                for (int j = 0; j < cols.length; j++) {
                    if (j >= headers.size()) {
                        headers.add("[" + j + "]");
                    }
                    record.put(headers.get(j), cols[j]);
                }
                records.add(record);
            }
        }
        return records;
    }
    
}
