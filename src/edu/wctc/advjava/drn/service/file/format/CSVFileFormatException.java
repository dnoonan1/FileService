package edu.wctc.advjava.drn.service.file.format;

import edu.wctc.advjava.drn.service.file.FileFormatException;

/**
 *
 * @author Dan
 */
public class CSVFileFormatException extends FileFormatException {

    /**
     * Creates a new instance of <code>FileFormatException</code> without detail
     * message.
     */
    public CSVFileFormatException() {
    }

    /**
     * Constructs an instance of <code>FileFormatException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CSVFileFormatException(String msg) {
        super(msg);
    }
    
}
