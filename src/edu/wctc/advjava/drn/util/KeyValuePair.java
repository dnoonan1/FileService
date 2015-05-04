package edu.wctc.advjava.drn.util;

import java.util.Objects;

/**
 * This class is used to represent a key-value pair, consisting of a key (unique
 * identifier) plus a value associated with that key.
 * 
 * @author Dan Noonan
 * @param <K> the key type
 * @param <V> the value type
 */
public class KeyValuePair<K, V> {

    private final K key;
    private final V value;

    /**
     * Constructs a new from the given key and value.
     * 
     * @param key the key
     * @param value the value associated with the given key
     */
    public KeyValuePair(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        this.key = key;
        this.value = value; // no validation - any value is acceptable
    }

    /**
     * Gets the key from this {@code KeyValuePair}.
     * 
     * @return the key for this KeyValuePair 
     */
    public final K getKey() {
        return key;
    }

    /**
     * Gets the value from this {@code KeyValuePair}.
     * 
     * @return the value for this KeyValuePair 
     */
    public final V getValue() {
        return value;
    }

    /**
     * Gets the hash code for this {@code KeyValuePair}.
     * 
     * @return the hash code for this object
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.key);
        hash = 29 * hash + Objects.hashCode(this.value);
        return hash;
    }

    /**
     * Compares this {@code KeyValuePair} with the specified {@code Object} for
     * equality. The result is true if and only if the {@code Object} is a 
     * {@code KeyValuePair} equivalent to this {@code KeyValuePair}.
     * 
     * @param obj the Object to compare with
     * @return true if the given Object is a KeyValuePair equivalent to this 
     *     KeyValuePair, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof KeyValuePair) {
            KeyValuePair that = (KeyValuePair)obj;
            return this.key   .equals( that.key )
                && this.value .equals( that.value );
        }
        return false;
    }

    /**
     * Returns a {@code String} representation of this {@code KeyValuePair}.
     * 
     * @return a String representation of this KeyValuePair 
     */
    @Override
    public String toString() {
        return "KeyValuePair{" + "key=" + key + ", value=" + value + '}';
    }
    
}
