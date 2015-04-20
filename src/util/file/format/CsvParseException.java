package util.file.format;

import java.text.ParseException;

/**
 *
 * @author Dan
 */
public class CsvParseException extends ParseException {

    private int lineNumber;
    
    /**
     * Creates a new instance of <code>CsvParseException</code> without detail
     * message.
     */
//    public CsvParseException() {
//    }

    /**
     * Constructs an instance of <code>CsvParseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CsvParseException(String msg, int errorOffset) {
        super(msg, errorOffset);
    }

    public int getLineNumber() {
        return lineNumber;
    }
    
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
}
