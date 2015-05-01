package edu.wctc.advjava.drn.util;

import java.text.ParseException;

/**
 *
 * @author Dan
 * @param <T>
 */
public interface Parser<T> {
    T parse(String s) throws ParseException;
}
