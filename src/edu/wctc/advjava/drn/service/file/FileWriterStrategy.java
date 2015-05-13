package edu.wctc.advjava.drn.service.file;

import java.io.IOException;

/**
 * This interface is a strategy for writing generic data to files.
 * 
 * @author Dan Noonan
 * @param <T> the type of the generic data object
 * @see FileServiceStrategy
 * @see FileService
 */
public interface FileWriterStrategy<T> { 
    
    /**
     * Writes the specified generic data object to a file. If the file does not
     * exist, a new file will be created. If the file does exist, the {@code 
     * append} argument specifies whether the file will be overwritten or 
     * appended to.
     * 
     * @param data the data object to write to file
     * @param append whether to append (true) or overwrite (false)
     * 
     * @throws IOException if an error occurs writing the file
     * @throws FileFormatException if the data object violates the required 
     *     format
     */
    public abstract void write(T data, boolean append)
            throws IOException, FileFormatException;  
    
}
