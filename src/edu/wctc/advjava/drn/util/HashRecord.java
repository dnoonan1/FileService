package edu.wctc.advjava.drn.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Dan Noonan
 * @see Record
 */
public class HashRecord extends HashMap<String, String> implements Record {

    private String title;
    
    public HashRecord() {}

    public HashRecord(String title) {
        setTitle(title);
    }
    
    public HashRecord(Map<String, String> map) {
        super(map);
    }
    
    public HashRecord(Map<String, String> map, String title) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode((HashMap)this);
        hash = 79 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HashRecord) {
            HashRecord that = (HashRecord)obj;
            return this.title.equals(that.title)
                    && ((HashMap)this).equals((HashMap)that);
        }
        return false;
    }

    @Override
    public String toString() {
        return "HashRecord{"
                + "\n\ttitle=" + title
                + "\n\tHashMap=" + (HashMap)this
        + '}';
    }
    
}
