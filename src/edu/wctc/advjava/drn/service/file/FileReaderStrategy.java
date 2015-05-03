package edu.wctc.advjava.drn.service.file;

import java.io.IOException;

/**
 *
 * @author Dan
 */
public interface FileReaderStrategy<T> {
    public abstract T read() throws IOException, FileFormatException;    
}
