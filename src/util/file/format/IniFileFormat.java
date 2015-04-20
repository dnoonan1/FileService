package util.file.format;

import util.file.FileFormat;
import util.LinkedRecord;
import util.Record;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dan
 */
public class IniFileFormat implements FileFormat {
    
    private static final String SEMICOLON = ";";
    private static final String HASHTAG = "#";
    private static final String SECTION = "[]";
    private static final String SECTION_START = "[";
    private static final String SECTION_END = "]";
    private static final String EQUALS = "=";
    private static final String COLON = ":";
    private static final String SEPARATOR = "=|:";
    private static final String NEWLINE = String.format("%n");
    
    private boolean space;
    private boolean useEquals;

    public IniFileFormat() {
        this(false, true);
    }

    public IniFileFormat(boolean space) {
        this(space, true);
    }
    
    public IniFileFormat(boolean space, boolean useEquals) {
        this.space = space;
        this.useEquals = useEquals;
    }

    @Override
    public String encode(List<Record> records) {
        StringBuilder data = new StringBuilder();
        int start, end;
        String separator;
        if (useEquals) {
            separator = "=";
        } else {
            separator = ":";
        }
        if (space) {
            separator = " " + separator + " ";
        }
        for (Record rec : records) {
            Set<String> keys = rec.keySet();
            for (String key : keys) {
                if (key.equals(SECTION)) {
                    data.append(SECTION_START)
                        .append(rec.get(SECTION))
                        .append(SECTION_END);
                } else {
                    data.append(key)
                        .append(separator)
                        .append(rec.get(key));
                }
                data.append(NEWLINE);
            }
            data.append(NEWLINE);
        }
        start = data.length() - 2;
        end = data.length();
        return data.delete(start, end).toString();
    }

    @Override
    public List<Record> decode(String data) {
        
        List<Record> records = new ArrayList<>();
        Record record = new LinkedRecord();
        String[] lines = data.split(NEWLINE);
            
        for (int i = 0; i < lines.length; i++) {

            String line = lines[i];

            // Ignore empty lines
            // or lines that start with ; or # (comment lines)
            if (line.isEmpty()
                    || line.startsWith(SEMICOLON)
                    || line.startsWith(HASHTAG)) {
                continue;
            }
            
            // All other lines are either (1) section titles or (2) name-value
            // pairs separated by =, with the following format:
            //     1. [SECTION NAME]
            //     2. NAME = VALUE
            if (line.contains(SECTION_START)
                    && line.contains(SECTION_END)) {
                // Start of a new section
                if (!record.isEmpty()) {
                    records.add(record);
                    record = new LinkedRecord();
                }
                int start = line.indexOf(SECTION_START);
                int end = line.indexOf(SECTION_END);
                String title = line.substring(start+1, end);
                record.put(SECTION, title);
            } else {
                // Name-value pair separated by equals (=) or colon (:)
                String[] pair = line.split(SEPARATOR);
                record.put(pair[0].trim(), pair[1].trim());
            }
        }
        
        records.add(record); // add final record
            
        return records;
    }
    
}
