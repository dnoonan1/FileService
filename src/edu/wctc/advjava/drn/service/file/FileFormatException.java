package edu.wctc.advjava.drn.service.file;

/**
 *
 * @author Dan
 */
public class FileFormatException extends Exception {

    /**
     * Creates a new instance of <code>FileFormatException</code> without detail
     * message.
     */
    public FileFormatException() {}

    /**
     * Constructs an instance of <code>FileFormatException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FileFormatException(String msg) {
        super(msg);
    }
    
}
