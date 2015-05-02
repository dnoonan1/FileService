package edu.wctc.advjava.drn.service.file.format;

import edu.wctc.advjava.drn.service.file.FileFormat;
import edu.wctc.advjava.drn.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dan
 */
public class INIFileFormat implements FileFormat {
    
    private static final String SEMICOLON = ";";
    private static final String HASHTAG = "#";
    private static final String SECTION_START = "[";
    private static final String SECTION_END = "]";
    private static final String EQUALS = "=";
    private static final String COLON = ":";
    private static final String SEPARATOR = "=|:";
    private static final String SPACE = " ";
    private static final String NEWLINE = String.format("%n");
    
    private boolean space;
    private boolean useColon;

    public INIFileFormat() {
        this(false, false);
    }

    public INIFileFormat(boolean space) {
        this(space, false);
    }
    
    public INIFileFormat(boolean space, boolean useColon) {
        this.space = space;
        this.useColon = useColon;
    }

    @Override
    public final String encode(List<Record> records) {
        StringBuilder data = new StringBuilder();
        int start, end;
        String separator;
        if (useColon) {
            separator = COLON;
        } else {
            separator = EQUALS;
        }
        if (space) {
            separator = SPACE + separator + SPACE;
        }
        for (Record rec : records) {
            data.append(SECTION_START)
                .append(rec.getTitle())
                .append(SECTION_END);
            Set<String> keys = rec.keySet();
            for (String key : keys) {
                data.append(key)
                    .append(separator)
                    .append(rec.get(key))
                    .append(NEWLINE);
            }
            data.append(NEWLINE);
        }
        start = data.length() - 2 * NEWLINE.length();
        end = data.length();
        return data.delete(start, end).toString();
    }

    @Override
    public final List<Record> decode(String data) throws INIParseException {
        
        List<Record> records = new ArrayList<>();
        Record record = new LinkedRecord();
        String[] lines = data.split(NEWLINE);
            
        for (int i = 0; i < lines.length; i++) {

            String line = lines[i].trim();

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
            if (isSectionHeader(line)) {
                // Start of a new section
                if (!record.isEmpty()) {
                    records.add(record);
                    record = new LinkedRecord();
                }
//                int start = line.indexOf(SECTION_START);
//                int end = line.indexOf(SECTION_END);
//                String title = line.substring(start+1, end);
                String title = parseTitle(line);
                record.setTitle(title);
            } else {
//                // Name-value pair separated by equals (=) or colon (:)
//                String[] pair = line.split(SEPARATOR);
//                if (pair.length != 2) {
//                    INIParseException e = new INIParseException(
//                            "expected format\"name=value\"\n"
//                            + line + "<-- error",
//                            line.length()
//                    );
//                    e.setLineNumber(i + 1);
//                    throw e;
//                }
//                record.put(pair[0].trim(), pair[1].trim());
                try {
                    KeyValuePair<String, String> kv = parseLine(line);
                    record.put(kv.getKey(), kv.getValue());
                } catch (INIParseException e) {
                    e.setLineNumber(i);
                    throw e;
                } 
            }
        }
        
        records.add(record); // add final record
            
        return records;
    }
    
    public final static boolean isSectionHeader(final String line) {
        return line.startsWith(SECTION_START) && line.endsWith(SECTION_END);
    }
    
    public final static String parseTitle(final String line) {
        if (line.length() > 2) {
            String title = line.substring(1, line.length() - 2);
            return title;
        } else {
            return null;
        }
    }
    
    public final static
        KeyValuePair<String, String> parseLine(final String line)
            throws INIParseException {
        String[] pair = line.split(SEPARATOR);
        if (pair.length != 2) {
            throw new INIParseException(
                "expected format\"name=value\"\n"
                + line + "<-- error",
                line.length()
            );
        }
        return new KeyValuePair(pair[0].trim(), pair[1].trim());
    }
    
}
