package edu.wctc.advjava.drn.service.file.format;

import edu.wctc.advjava.drn.service.file.RecordFileFormat;
import edu.wctc.advjava.drn.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class represents the INI (Initialization) file format. The INI file 
 * format consists of sections. A section name is denoted between brackets 
 * ({@code []}). Each section has properties with values, formatted as 
 * <i>name</i>=<i>value</i>. Thus, the total file format may be represented as 
 * follows:
 * <blockquote>
 * [<i>Section 1</i>]
 * <i>property 1</i>=<i>value 1</i>
 * <i>property 2</i>=<i>value 2</i>
 * ...
 * <i>property 3</i>=<i>value </i>n<br><br>
 * [<i>Section 2</i>]
 * <i>property 1</i>=<i>value 1</i>
 * <i>property 2</i>=<i>value 2</i>
 * ...
 * <i>property 3</i>=<i>value </i>n<br><br>
 * .<br>
 * .<br>
 * .<br><br>
 * [<i>Section </i>n]
 * <i>property 1</i>=<i>value 1</i>
 * <i>property 2</i>=<i>value 2</i>
 * ...
 * <i>property 3</i>=<i>value </i>n
 * </blockquote>
 * 
 * @author Dan Noonan
 * @see RecordFileFormat
 * @see Record
 * @see LineParser
 */
public class INIFileFormat
    implements RecordFileFormat, LineParser<KeyValuePair<String, String>> {
    
    private static final String SECTION_START = "[";
    private static final String SECTION_END = "]";
    private static final String SEPARATOR = "=";

    /**
     * Constructs a new instance of INIFileFormat.
     */
    public INIFileFormat() {}

    /**
     * Encodes the given {@code List} of {@code Record}s as a {@code String} of
     * INI-formatted text.
     * 
     * @param records the List of Records to encode
     * @return the Records, formatted as an INI 
     */
    @Override
    public final String encode(List<Record> records) {
        StringBuilder data = new StringBuilder();
        int start, end;
        for (Record rec : records) {
            data.append(SECTION_START)
                .append(rec.getTitle())
                .append(SECTION_END)
                .append(NEWLINE);
            Set<String> keys = rec.keySet();
            for (String key : keys) {
                data.append(key)
                    .append(SEPARATOR)
                    .append(rec.get(key))
                    .append(NEWLINE);
            }
            data.append(NEWLINE);
        }
        start = data.length() - 2 * NEWLINE.length();
        end = data.length();
        return data.delete(start, end).toString();
    }

    /**
     * Decodes the given String of INI-formatted data into a {@code List} of
     * {@code Record}s.
     * 
     * @param data the INI-formatted data to decode
     * @return a List of Records
     * @throws INIParseException if the given data does not follow the INI 
     *      format
     */
    @Override
    public final List<Record> decode(String data) throws INIParseException {
        
        List<Record> records = new ArrayList<>();
        Record record = new LinkedRecord();
        String[] lines = data.split(NEWLINE);
        String title;
            
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
            if ((title = parseSectionHeader(line)) != null) {
                // Start of a new section
                if (!record.isEmpty()) {
                    records.add(record);
                    record = new LinkedRecord();
                }
                record.setTitle(title);
            } else {
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
    
    /**
     * Parses the given line. If it is a section header (a name between brackets
     * []), the section name is returned. Otherwise, the line is not a section 
     * header and {@code null} is returned.
     * 
     * @param line the line to parse
     * @return the section name if the given line represents a section header, 
     *     otherwise null
     */
    public final String parseSectionHeader(String line) {
        line = line.trim();
        if (line.length() > 2 
                && line.startsWith(SECTION_START) 
                && line.endsWith(SECTION_END)) {
            String title = line.substring(1, line.length() - 1);
            return title;
        } else {
            return null;
        }
    }
    
    /**
     * Parses the given line and returns the data as a {@code KeyValuePair}. The
     * key represents the property name, and the value represents the property's
     * value.
     * 
     * @param line the line to parse
     * @return a KeyValuePair containing the property name and its value
     * @throws INIParseException if the given line does not follow the INI file
     *     format
     */
    @Override
    public final
        KeyValuePair<String, String> parseLine(final String line)
            throws INIParseException {
        String[] pair = line.split(SEPARATOR);
        if (pair.length != 2) {
            throw new INIParseException(
                "expected format\"property=value\"\n"
                + line + "<-- error",
                line.length()
            );
        }
        return new KeyValuePair(pair[0].trim(), pair[1].trim());
    }

    /**
     * Gets the hash code for this {@code INIFileFormat}.
     * 
     * @return the hash code for this object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Compares this {@code INIFileFormat} with the specified {@code Object} for
     * equality. The result is true if and only if the {@code Object} is a 
     * {@code INIFileFormat} equivalent to this {@code INIFileFormat}.
     * 
     * @param obj the object to be compared with
     * @return true if the given Object is a INIFileFormat equivalent to this 
     *     INIFileFormat, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof INIFileFormat) {
            return true;
        }
        return false;
    }

    /**
     * Returns a {@code String} representation of this {@code INIFileFormat}.
     * 
     * @return a String representation of this object 
     */
    @Override
    public String toString() {
        return "INIFileFormat{" + '}';
    }
    
}
