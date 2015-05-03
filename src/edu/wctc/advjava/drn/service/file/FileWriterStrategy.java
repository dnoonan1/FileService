package edu.wctc.advjava.drn.service.file;

import java.io.IOException;

/**
 *
 * @author Dan
 */
public interface FileWriterStrategy<T> {    
    public abstract void write(T data, boolean append)
            throws IOException, FileFormatException;   
}
