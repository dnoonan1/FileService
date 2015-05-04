package edu.wctc.advjava.drn.util;

/**
 * This interface is meant to be implemented by classes that can parse a single
 * line of text (represented by a {@code String}). It has a single method,
 * {@code parseLine()}, which returns the type given by the generic parameter
 * {@code T}.
 * 
 * @author Dan
 * @param <T> the type returned by this LineParser's parseLine method
 */
public interface LineParser<T> {
    <X extends Exception> T parseLine(String line) throws X;
}
