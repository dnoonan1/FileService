package edu.wctc.advjava.drn.util;

/**
 *
 * @author Dan
 */
public class KeyValuePair<K, V> {

    private final K key;
    private final V value;

    public KeyValuePair(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        this.key = key;
        this.value = value; // no validation - any value is acceptable
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }
    
}
