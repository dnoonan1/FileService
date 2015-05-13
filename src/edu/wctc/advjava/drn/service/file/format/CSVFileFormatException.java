package edu.wctc.advjava.drn.service.file.format;

import edu.wctc.advjava.drn.service.file.FileFormatException;

/**
 * This exception class is for exceptions thrown by the {@code CSVFileFormat}
 * methods.
 * 
 * @author Dan Noonan
 * @see CSVFileFormat
 */
public class CSVFileFormatException extends FileFormatException {

    /**
     * Creates a new instance of <code>CSVFileFormatException</code> without detail
     * message.
     */
    public CSVFileFormatException() {
    }

    /**
     * Constructs an instance of <code>CSVFileFormatException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CSVFileFormatException(String msg) {
        super(msg);
    }
    
}
