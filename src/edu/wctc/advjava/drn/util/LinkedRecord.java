package edu.wctc.advjava.drn.util;

import java.util.*;

/**
 * A {@code LinkedRecord} is a specific implementation of {@code Record}. Since 
 * the {@code Record} interface is an extension of 
 * {@code Map&lt;String, String, &gt;}, {@code LinkedRecord} must implement the 
 * {@code Map} interface. It does so merely by extending 
 * {@code LinkedHashMap&lt;String, String, &gt;}. Thus, {@code LinkedRecord} is 
 * a {@code LinkedHashMap&lt;String, String, &gt;} with one additional property 
 * - a title.
 * 
 * @author Dan Noonan
 * @see Record
 */
public class LinkedRecord extends LinkedHashMap<String, String>
    implements Record {

    private String title;
    
    /**
     * Constructs a new {@code LinkedRecord} instance with an empty {@code Map}
     * and a {@code null} title.
     */
    public LinkedRecord() {}

    /**
     * Constructs a new {@code LinkedRecord} instance an empty {@code Map} and
     * the given title.
     * 
     * @param title the LinkedRecord's title
     */
    public LinkedRecord(String title) {
        setTitle(title);
    }
    
    /**
     * Constructs a new {@code LinkedRecord} instance from the given {@code Map}.
     * The new {@code LinkedRecord} gets all of the keys and values from the 
     * {@code Map}. The title property is set to {@code null} by default.
     * 
     * @param map the Map whose mappings are to be placed in this Map 
     */
    public LinkedRecord(Map<String, String> map) {
        super(map);
    }
    
    /**
     * Constructs a new {@code LinkedRecord} instance from the given map, with 
     * the given title. The new {@code LinkedRecord} gets all of the keys and 
     * values from the {@code LinkedMap}.
     * 
     * @param map the Map whose mappings are to be placed in this Map
     * @param title the LinkedRecord's title 
     */
    public LinkedRecord(Map<String, String> map, String title) {
        super(map);
        setTitle(title);
    }
    
    /**
     * Gets the title for this {@code LinkedRecord}.
     * 
     * @return the title for this LinkedRecord
     */
    @Override
    public final String getTitle() {
        return title;
    }

    /**
     * Sets the title for this {@code LinkedRecord}.
     * 
     * @param title the new title for this LinkedRecord
     */
    @Override
    public final void setTitle(String title) {
        // No validation - allow null and empty titles
        this.title = title;
    }

    /**
     * Gets the hash code for this {@code LinkedRecord}.
     * 
     * @return the hash code for this LinkedRecord. 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode((LinkedHashMap)this);
        hash = 79 * hash + Objects.hashCode(this.title);
        return hash;
    }

    /**
     * Compares this {@code LinkedRecord} with the specified {@code Object} for 
     * equality. The result is true if and only if the {@code Object} is a 
     * {@code LinkedRecord} equivalent to this {@code LinkedRecord}.
     * 
     * @param obj the Object to compare with
     * @return true if the given object is a LinkedRecord equivalent to this 
     *     LinkedRecord, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedRecord) {
            LinkedRecord that = (LinkedRecord)obj;
            return this.title.equals(that.title)
                    && ((HashMap)this).equals((HashMap)that);
        }
        return false;
    }

    /**
     * Returns a {@code String} representation of this {@code LinkedRecord}.
     * 
     * @return a String representation of this LinkedRecord 
     */
    @Override
    public String toString() {
        return "LinkedRecord{"
                + "title=" + title
                + ", map=" + (LinkedHashMap)this
        + '}';
    }
    
}
