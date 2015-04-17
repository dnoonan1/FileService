package util.file.format;

import util.file.FileFormat;
import util.file.LinkedRecord;
import util.file.Record;
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
    private static final String SECTION = "section";
    private static final String SECTION_START = "[";
    private static final String SECTION_END = "]";
    private static final String EQUALS = "=";
    private static final String NEWLINE = String.format("%n");

    @Override
    public String encode(List<Record> records) {
        StringBuilder data = new StringBuilder();
        for (Record rec : records) {
            Set<String> keys = rec.keySet();
            for (String key : keys) {
                if (key.equals(SECTION)) {
                    data.append(SECTION_START)
                        .append(rec.get(SECTION))
                        .append(SECTION_END)
                        .append(NEWLINE);
                } else {
                    data.append(key)
                        .append(EQUALS)
                        .append(rec.get(key))
                        .append(NEWLINE);
                }
            }
        }
        return data.toString();
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
                // Name-value pair separated by =
                String[] pair = line.split(EQUALS);
                record.put(pair[0].trim(), pair[1].trim());
            }
        }
        
        records.add(record); // add final record
            
        return records;
    }
    
}
