package edu.wctc.advjava.drn.util;

import java.util.LinkedHashMap;

/**
 *
 * @author Dan
 */
public class LinkedRecord extends LinkedHashMap<String, String>
    implements Record {

    private String title;
    
    public LinkedRecord() {}

    public LinkedRecord(String title) {
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
