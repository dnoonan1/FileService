package edu.wctc.advjava.drn.service.file;

import java.io.IOException;

/**
 * This interface is a strategy for reading files and returning generic data.
 * 
 * @author Dan Noonan
 * @param <T> the type of the generic data object
 * @see FileServiceStrategy
 * @see FileService
 */
public interface FileReaderStrategy<T> {
    
    /**
     * Reads a file, returning a generic data object.
     * 
     * @return the file data, converted to a generic data object
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format
     */
    public abstract T read() throws IOException, FileFormatException;
    
}
