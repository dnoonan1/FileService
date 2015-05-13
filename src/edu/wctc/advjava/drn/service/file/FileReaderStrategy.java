package edu.wctc.advjava.drn.service.file;

import java.io.IOException;

/**
 * This interface is a strategy for reading files and returning generic data.
 * 
 * @author Dan Noonan
 * @see FileService
 */
public interface FileReaderStrategy<T> {
    public abstract T read() throws IOException, FileFormatException;    
}
