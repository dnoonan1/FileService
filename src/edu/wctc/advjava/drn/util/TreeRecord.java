package edu.wctc.advjava.drn.util;

import java.util.TreeMap;

/**
 *
 * @author Dan
 */
public class TreeRecord extends TreeMap<String, String> implements Record {

    private String title;
    
    public TreeRecord() {}

    public TreeRecord(String title) {
        // No validation - see setTitle() below
        this.title = title;
    }
    
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        // No validation - allow null and empty titles
        this.title = title;
    }
    
}
