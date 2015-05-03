package edu.wctc.advjava.drn.service.file;

/**
 *
 * @author Dan
 */
public interface FileFormat<T> {
    String encode(T data) throws FileFormatException;
    T decode(String data) throws FileFormatException;    
}
