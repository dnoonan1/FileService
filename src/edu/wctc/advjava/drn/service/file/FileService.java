package edu.wctc.advjava.drn.service.file;

import java.io.IOException;

/**
 *
 * @author Dan
 */
public interface FileService<T> {
    
    public static final boolean APPEND = true;
    public static final boolean OVERWRITE = false;
    
    public abstract void setReader(FileReaderStrategy<T> reader);
    public abstract void setWriter(FileWriterStrategy<T> writer);
    public abstract T read() throws IOException, FileFormatException;
    public abstract void write(T data, boolean append)
            throws IOException, FileFormatException;
    public abstract void append(T data)
            throws IOException, FileFormatException;
    public abstract void overwrite(T data)
            throws IOException, FileFormatException;
    
}
