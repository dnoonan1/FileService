package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * This class is an implementation of {@code FileReaderStrategy}. It reads text
 * from a file and returns a list of records.
 * 
 * @author Dan Noonan
 * @see FileReaderStrategy
 */
public class TextFileReader implements FileReaderStrategy<List<Record>> {
    
    private final File file;
    private final FileFormat<List<Record>> format;
    
    /**
     * Constructs a new {@code TextFileReader} from the given data.
     * 
     * @param filePath the path of the file to read
     * @param format the format used to decode the file text
     * 
     * @throws FileNotFoundException if the specified file does not exist
     */
    public TextFileReader(final String filePath, final FileFormat<List<Record>> format)
            throws FileNotFoundException {
        this.file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        if (format == null) {
            throw new IllegalArgumentException();
        }
        this.format = format;
    }
    
    /**
     * Reads a file, returning a list of records.
     * 
     * @return the file data, converted to a list of records
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format
     */
    @Override
    public final List<Record> read()
            throws IOException, FileFormatException {

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String data = FileFormat.EMPTY_STRING;
            String line;
            while ((line = in.readLine()) != null) {
                data += line + FileFormat.Char.CR;
            }
            return format.decode(data);
        }
        
    }

    /**
     * Gets the hash code for this object;
     * 
     * @return the hash code for this object 
     */
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.file);
        hash = 59 * hash + Objects.hashCode(this.format);
        return hash;
    }

    /**
     * Compares this to the specified object for equality. The result is true if
     * and only if the specified object is equivalent to this.
     * 
     * @param obj the object to compare with
     * @return true if the specified object is equivalent to this, false 
     *     otherwise
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof TextFileReader) {
            final TextFileReader that = (TextFileReader)obj;
            return this.file  .equals( that.file )
                && this.format.equals( that.format );
        }
        return false;
    }

    /**
     * Returns a {@code String} representation of this object.
     * 
     * @return a String representation of this object
     */
    @Override
    public final String toString() {
        return "TextFileReader{" + "file=" + file + ", format=" + format + '}';
    }
    
}
