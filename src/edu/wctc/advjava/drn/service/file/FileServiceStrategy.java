package edu.wctc.advjava.drn.service.file;

import java.io.IOException;

/**
 * This interface is a strategy for a high-level service class that reads and 
 * writes files using a generic data type.
 * 
 * @author Dan Noonan
 * @param <T> the generic data object type
 */
public interface FileServiceStrategy<T> {
    
    public static final boolean APPEND = true;
    public static final boolean OVERWRITE = false;
    
    /**
     * Sets the reader for this {@code FileServiceStrategy}.
     * 
     * @param reader the new reader
     */
    public abstract void setReader(FileReaderStrategy<T> reader);
    
    /**
     * Sets the writer for this {@code FileServiceStrategy}.
     * 
     * @param writer the new writer 
     */
    public abstract void setWriter(FileWriterStrategy<T> writer);
    
    /**
     * Reads the whole file and returns a generic data object. 
     * 
     * @return a generic object representing the file data
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    public abstract T readAll() throws IOException, FileFormatException;
    
    /**
     * Writes a generic data object to file. 
     * 
     * @param data the data object to write to file
     * @param append whether to append (true) or overwrite (false)
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    public abstract void writeAll(T data, boolean append)
            throws IOException, FileFormatException;
    
    /**
     * Writes a generic data object to file, appending to the current file (if 
     * it already exists). 
     * 
     * @param data the data object to write to file
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    public abstract void append(T data)
            throws IOException, FileFormatException;
    
    /**
     * Writes a generic data object to file, overwriting the current file (if it
     * already exists). 
     * 
     * @param data the data object to write to file
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    public abstract void overwrite(T data)
            throws IOException, FileFormatException;
    
}
