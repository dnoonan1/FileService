package edu.wctc.advjava.drn.service.file;

/**
 *
 * @author Dan
 * @param <T>
 * @see RecordFileFormat
 */
public interface FileFormat<T> {
    
    String encode(T data) throws FileFormatException;
    T decode(String data) throws FileFormatException;
    
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
    
    public static class Char {
        public static final char CR = '\n';
        public static final char LF = '\r';
        public static final char QUOTE = '\"';
        public static final char SINGLE_QUOTE = '\'';
    }
    
}
