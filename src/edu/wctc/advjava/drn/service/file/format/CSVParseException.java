package edu.wctc.advjava.drn.service.file.format;

/**
 *
 * @author Dan
 */
public class CSVParseException extends CSVFileFormatException {

    private int lineNumber;
    private int errorOffset;
    
    /**
     * Creates a new instance of <code>CsvParseException</code> without detail
     * message.
     */
//    public CSVParseException() {
//    }

    /**
     * Constructs an instance of <code>CsvParseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param errorOffset
     */
    public CSVParseException(String msg, int errorOffset) {
        super(msg);
        setErrorOffset(errorOffset);
    }

    public int getErrorOffset() {
        return errorOffset;
    }
    
    private void setErrorOffset(int errorOffset) {
        this.errorOffset = errorOffset;
    }
    
    public int getLineNumber() {
        return lineNumber;
    }
    
    public final void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
}
