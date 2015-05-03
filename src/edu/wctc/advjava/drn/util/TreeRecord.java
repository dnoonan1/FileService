package edu.wctc.advjava.drn.util;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Dan
 */
public class TreeRecord extends TreeMap<String, String> implements Record {

    private String title;
    
    public TreeRecord() {}

    public TreeRecord(String title) {
        setTitle(title);
    }
    
    public TreeRecord(Map<String, String> map) {
        super(map);
    }
    
    public TreeRecord(Map<String, String> map, String title) {
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
