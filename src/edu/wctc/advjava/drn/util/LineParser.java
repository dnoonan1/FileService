package edu.wctc.advjava.drn.util;

import java.text.ParseException;

/**
 *
 * @author Dan
 * @param <T>
 */
public interface LineParser<T> {
    <X extends Exception> T parseLine(String line) throws X;
}
