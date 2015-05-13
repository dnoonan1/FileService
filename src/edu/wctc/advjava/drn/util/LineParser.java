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
    
    /**
     * Parses a single line and returns a generic object representing parsed 
     * data.
     * 
     * @param <X> the exception thrown by this parser
     * @param line the line to be parsed
     * @return a generic object representing parsed data
     * @throws X the Exception thrown by this parser if the format is incorrect
     */
    <X extends Exception> T parseLine(String line) throws X;
    
}
