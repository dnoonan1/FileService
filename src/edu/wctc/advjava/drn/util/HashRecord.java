package edu.wctc.advjava.drn.util;

import java.util.*;

/**
 * A {@code HashRecord} is a specific implementation of {@code Record}. Since 
 * the {@code Record} interface is an extension of 
 * {@code Map&lt;String, String, &gt;}, {@code HashRecord} must implement the 
 * {@code Map} interface. It does so merely by extending 
 * {@code HashMap&lt;String, String, &gt;}. Thus, {@code HashRecord} is a
 * {@code HashMap&lt;String, String, &gt;} with one additional property - a 
 * title.
 * 
 * @author Dan Noonan
 * @see Record
 */
public class HashRecord extends HashMap<String, String> implements Record {

    private String title;
    
    /**
     * Constructs a new {@code HashRecord} instance with an empty {@code Map}
     * and a {@code null} title.
     */
    public HashRecord() {}

    /**
     * Constructs a new {@code HashRecord} instance an empty {@code Map} and
     * the given title.
     * 
     * @param title the HashRecord's title
     */
    public HashRecord(String title) {
        setTitle(title);
    }
    
    /**
     * Constructs a new {@code HashRecord} instance from the given {@code Map}.
     * The new {@code HashRecord} gets all of the keys and values from the 
     * {@code Map}. The title property is set to {@code null} by default.
     * 
     * @param map the Map whose mappings are to be placed in this Map 
     */
    public HashRecord(Map<String, String> map) {
        super(map);
    }
    
    /**
     * Constructs a new {@code HashRecord} instance from the given map, with 
     * the given title. The new {@code HashRecord} gets all of the keys and 
     * values from the {@code Map}.
     * 
     * @param map the Map whose mappings are to be placed in this Map
     * @param title the HashRecord's title 
     */
    public HashRecord(Map<String, String> map, String title) {
        super(map);
        setTitle(title);
    }
    
    /**
     * Gets the title for this {@code HashRecord}.
     * 
     * @return the title for this HashRecord
     */
    @Override
    public final String getTitle() {
        return title;
    }

    /**
     * Sets the title for this {@code HashRecord}.
     * 
     * @param title the new title for this HashRecord
     */
    @Override
    public final void setTitle(String title) {
        // No validation - allow null and empty titles
        this.title = title;
    }

    /**
     * Gets the hash code for this {@code HashRecord}.
     * 
     * @return the hash code for this HashRecord. 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode((HashMap)this);
        hash = 79 * hash + Objects.hashCode(this.title);
        return hash;
    }

    /**
     * Compares this {@code HashRecord} with the specified {@code Object} for 
     * equality. The result is true if and only if the {@code Object} is a 
     * {@code HashRecord} equivalent to this {@code HashRecord}.
     * 
     * @param obj the Object to compare with
     * @return true if the given object is a HashRecord equivalent to this 
     *     HashRecord, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HashRecord) {
            HashRecord that = (HashRecord)obj;
            return this.title.equals(that.title)
                    && ((HashMap)this).equals((HashMap)that);
        }
        return false;
    }

    /**
     * Returns a {@code String} representation of this {@code HashRecord}.
     * 
     * @return a String representation of this HashRecord 
     */
    @Override
    public String toString() {
        return "HashRecord{"
                + "title=" + title
                + ", map=" + (HashMap)this
        + '}';
    }
    
}
