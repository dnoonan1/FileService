package edu.wctc.advjava.drn.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Dan
 */
public class LinkedRecord extends LinkedHashMap<String, String>
    implements Record {

    private String title;
    
    public LinkedRecord() {}

    public LinkedRecord(String title) {
        setTitle(title);
    }
    
    public LinkedRecord(Map<String, String> map) {
        super(map);
    }
    
    public LinkedRecord(Map<String, String> map, String title) {
        super(map);
        setTitle(title);
    }
    
    @Override
    public final String getTitle() {
        return title;
    }

    @Override
    public final void setTitle(String title) {
        // No validation - allow null and empty titles
        this.title = title;
    }
    
}
