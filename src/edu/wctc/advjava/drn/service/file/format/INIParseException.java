package edu.wctc.advjava.drn.service.file.format;

/**
 * This exception class is for parse exceptions thrown by the {@code INIFileFormat}
 * methods.
 * 
 * @author Dan Noonan
 * @see CSVFileFormat
 */
public class INIParseException extends INIFileFormatException {

    private int lineNumber;
    private int errorOffset;

    /**
     * Constructs an instance of <code>INIParseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param errorOffset
     */
    public INIParseException(String msg, int errorOffset) {
        super(msg);
        setErrorOffset(errorOffset);
    }

    
    /**
     * Gets the error offset for this <code>INIParseException</code>, i.e., the
     * index of the parse error.
     * 
     * @return the error offset
     */
    public final int getErrorOffset() {
        return errorOffset;
    }
    
    private void setErrorOffset(int errorOffset) {
        this.errorOffset = errorOffset;
    }
    
    /**
     * Gets the line number of the parse error.
     * 
     * @return the line number of the parse error 
     */
    public final int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * Sets the line number of the parse error
     * 
     * @param lineNumber the new line number
     */
    public final void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
}
