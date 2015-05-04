package edu.wctc.advjava.drn.util;

import java.util.*;

/**
 * A {@code TreeRecord} is a specific implementation of {@code Record}. Since 
 * the {@code Record} interface is an extension of 
 * {@code Map&lt;String, String, &gt;}, {@code TreeRecord} must implement the 
 * {@code Map} interface. It does so merely by extending 
 * {@code TreeMap&lt;String, String, &gt;}. Thus, {@code TreeRecord} is a
 * {@code TreeMap&lt;String, String, &gt;} with one additional property - a 
 * title.
 * 
 * @author Dan Noonan
 * @see Record
 */
public class TreeRecord extends TreeMap<String, String> implements Record {

    private String title;
    
    /**
     * Constructs a new {@code TreeRecord} instance with an empty {@code Map}
     * and a {@code null} title.
     */
    public TreeRecord() {}

    /**
     * Constructs a new {@code TreeRecord} instance an empty {@code Map} and
     * the given title.
     * 
     * @param title the TreeRecord's title
     */
    public TreeRecord(String title) {
        setTitle(title);
    }
    
    /**
     * Constructs a new {@code TreeRecord} instance from the given {@code Map}.
     * The new {@code TreeRecord} gets all of the keys and values from the 
     * {@code Map}. The title property is set to {@code null} by default.
     * 
     * @param map the Map whose mappings are to be placed in this Map 
     */
    public TreeRecord(Map<String, String> map) {
        super(map);
    }
    
    /**
     * Constructs a new {@code TreeRecord} instance from the given map, with 
     * the given title. The new {@code TreeRecord} gets all of the keys and 
     * values from the {@code Map}.
     * 
     * @param map the Map whose mappings are to be placed in this Map
     * @param title the TreeRecord's title 
     */
    public TreeRecord(Map<String, String> map, String title) {
        super(map);
        setTitle(title);
    }
    
    /**
     * Gets the title for this {@code TreeRecord}.
     * 
     * @return the title for this TreeRecord
     */
    @Override
    public final String getTitle() {
        return title;
    }

    /**
     * Sets the title for this {@code TreeRecord}.
     * 
     * @param title the new title for this TreeRecord
     */
    @Override
    public final void setTitle(String title) {
        // No validation - allow null and empty titles
        this.title = title;
    }

    /**
     * Gets the hash code for this {@code TreeRecord}.
     * 
     * @return the hash code for this TreeRecord. 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode((TreeMap)this);
        hash = 71 * hash + Objects.hashCode(this.title);
        return hash;
    }

    /**
     * Compares this {@code TreeRecord} with the specified {@code Object} for 
     * equality. The result is true if and only if the {@code Object} is a 
     * {@code TreeRecord} equivalent to this {@code TreeRecord}.
     * 
     * @param obj the Object to compare with
     * @return true if the given object is a Record equivalent to this 
     *     TreeRecord, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TreeRecord) {
            TreeRecord that = (TreeRecord)obj;
            return this.title.equals(that.title)
                    && ((TreeMap)this).equals((TreeMap)that);
        }
        return false;
    }

    /**
     * Returns a {@code String} representation of this {@code TreeRecord}.
     * 
     * @return a String representation of this TreeRecord 
     */
    @Override
    public String toString() {
        return "TreeRecord{"
                + "title=" + title
                + ", map=" + (TreeMap)this
        + '}';
    }
    
}
