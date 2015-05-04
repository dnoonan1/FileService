package edu.wctc.advjava.drn.util;

import java.util.Map;

/**
 * This class is used to represent a record, that is, a collection of data 
 * related to some object. For example, a person could be represented by the 
 * following data:
 * <blockquote>
 * <table>
 * <caption>Person</caption>
 * <tr><th>Property</th><th>Value</th></tr>
 * <tr><td>ID</td><td>1001</td></tr>
 * <tr><td>First Name</td><td>John</td></tr>
 * <tr><td>Last Name</td><td>Smith</td></tr>
 * <tr><td>Age</td><td>29</td></tr>
 * <tr><td>Phone</td><td>123-555-678</td></tr>
 * </table>
 * </blockquote>
 * <p>
 * In the above example, "Person" could be the title and each of the properties 
 * could be a key mapping to the corresponding value.
 * <p>
 * Abstractly, a {@code Record} is simply a {@code Map&lt;String, String&gt;} 
 * with one additional property - a title. The {@code Record} interface provides
 * access to that property via a getter and setter method.
 * 
 * @author Dan Noonan
 * @see HashRecord
 * @see LinkedRecord
 * @see TreeRecord
 */
public interface Record extends Map<String, String> {
    
    /**
     * Gets the title for this {@code Record}.
     * 
     * @return the title for this Record
     */
    public abstract String getTitle();
    
    /**
     * Sets the title for this {@code Record}.
     * 
     * @param title the new title for this Record
     */
    public abstract void setTitle(String title);
    
}
