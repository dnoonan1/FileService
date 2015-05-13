package edu.wctc.advjava.drn.service.file;

/**
 * This interface is to be implemented by strategy objects that encode generic
 * data to {@code String}s and decode {@code String}s to generic data.
 * 
 * @author Dan Noonan
 * @param <T> the generic data object
 */
public interface FileFormat<T> {
    
    // String constants to be used by implementation
    public static final String EMPTY_STRING = "";
    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String CR = "\n";
    public static final String CRLF = "\n\r";
    public static final String QUOTE = "\"";
    public static final String SEMICOLON = ";";
    public static final String HASHTAG = "#";
    public static final String EQUALS = "=";
    public static final String COLON = ":";
    public static final String SPACE = " ";
    
    // Character constants to be used by implementation
    public static class Char {
        public static final char CR = '\n';
        public static final char LF = '\r';
        public static final char QUOTE = '\"';
        public static final char SINGLE_QUOTE = '\'';
    }
    
    /**
     * Encode the specified generic data object to a {@code String}.
     * 
     * @param data the data object to be encoded
     * @return the data, encoded as a String
     * @throws FileFormatException if the data violates the format required by 
     *     the implementation
     */
    public abstract String encode(T data) throws FileFormatException;
    
    /**
     * Decode the specified {@code String} to a generic data object.
     * 
     * @param data the String to be decoded
     * @return the data, decoded as a generic data object
     * @throws FileFormatException if the data violates the format required by 
     *     the implementation 
     */
    public abstract T decode(String data) throws FileFormatException;
    
}
