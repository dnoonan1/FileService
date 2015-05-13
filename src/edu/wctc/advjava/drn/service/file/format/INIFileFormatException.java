package edu.wctc.advjava.drn.service.file.format;

import edu.wctc.advjava.drn.service.file.FileFormatException;

/**
 * This exception class is for exceptions thrown by the {@code INIFileFormat}
 * methods.
 * 
 * @author Dan Noonan
 * @see INIFileFormat
 */
public class INIFileFormatException extends FileFormatException {

    /**
     * Creates a new instance of <code>INIFileFormatException</code> without detail
     * message.
     */
    public INIFileFormatException() {
    }

    /**
     * Constructs an instance of <code>INIFileFormatException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public INIFileFormatException(String msg) {
        super(msg);
    }
    
}
