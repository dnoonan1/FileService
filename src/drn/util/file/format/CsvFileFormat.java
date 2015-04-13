package drn.util.file.format;

import drn.util.file.FileFormat;
import drn.util.file.LinkedRecord;
import drn.util.file.Record;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dan
 */
public class CsvFileFormat implements FileFormat {

    private static final boolean NO_HEADER = false;
    private static final String COMMA = ",";
    private static final String NEWLINE = String.format("%n");
    private static final String[] EMPTY_STRING_ARRAY = {};
    
    private String separator;
    private boolean hasHeader;

    public CsvFileFormat() {
        this(COMMA, NO_HEADER);
    }
    
    public CsvFileFormat(boolean hasHeader) {
        this(COMMA, hasHeader);
    }
    
    public CsvFileFormat(String separator) {
        this(separator, NO_HEADER);
    }
    
    public CsvFileFormat(String separator, boolean hasHeader) {
        setSeparator(separator);
        this.hasHeader = hasHeader; // no validation required (boolean)
    }

    public final void setSeparator(String separator) {
        if (separator == null || separator.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.separator = separator;
    }
    
    @Override
    public final String encodeAll(List<Record> records) {
        StringBuilder data = new StringBuilder();
        for (Record record : records) {
            for (String s : record.values()) {
                data.append(s).append(separator);
            }
            data.append(NEWLINE);
        }
        return data.toString();
    }

    @Override
    public final List<Record> decodeAll(String data) {
        List<Record> records = new ArrayList<>();
        Record record;
        String[] headers;
        String[] lines = data.split(NEWLINE);
        int startIndex;
        if (lines.length > 0) {
            if (hasHeader) {
                headers = lines[0].split(separator);
                startIndex = 1;
            } else {
                headers = EMPTY_STRING_ARRAY;
                startIndex = 0;
            }
            for (int i = startIndex; i < lines.length; i++) {
                record = new LinkedRecord();
                String[] cols = lines[i].split(separator);
                for (int j = 0; j < cols.length; j++) {
                    if (j < headers.length) {
                        record.put(headers[j], cols[j]);
                    } else {
                        record.put("[" + j + "]", cols[j]);
                    }
                }
                records.add(record);
            }
        }
        return records;
    }
    
}
