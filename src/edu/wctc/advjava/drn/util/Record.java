package edu.wctc.advjava.drn.util;

import java.util.Map;

/**
 *
 * @author Dan
 */
public interface Record extends Map<String, String> {
    public abstract String getTitle();
    public abstract void setTitle(String title);
}
