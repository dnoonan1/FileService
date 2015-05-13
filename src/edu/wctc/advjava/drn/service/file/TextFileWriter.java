package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * This class is an implementation of {@code FileWriterStrategy}. It writes 
 * a list of records to file.
 * 
 * @author Dan Noonan
 * @see FileWriterStrategy
 */
public class TextFileWriter implements FileWriterStrategy<List<Record>> {

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
    public TextFileWriter(final String filePath, final FileFormat<List<Record>> format)
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
     * Writes the specified records to a file. If the file does not
     * exist, a new file will be created. If the file does exist, the {@code 
     * append} argument specifies whether the file will be overwritten or 
     * appended to.
     * 
     * @param records the records to write to file
     * @param append whether to append (true) or overwrite (false)
     * 
     * @throws IOException if an error occurs writing the file
     * @throws FileFormatException if the data object violates the required 
     *     format
     */
    @Override
    public final void write(final List<Record> records, final boolean append)
            throws IOException, FileFormatException {
    
        try (BufferedWriter out =
                new BufferedWriter(
                    new FileWriter(file, append))) {
            out.write(format.encode(records));
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            throw e;
        }
        
    }

    /**
     * Gets the hash code for this object;
     * 
     * @return the hash code for this object 
     */
    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.file);
        hash = 83 * hash + Objects.hashCode(this.format);
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
        if (obj instanceof TextFileWriter) {
            TextFileWriter that = (TextFileWriter)obj;
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
        return "TextFileWriter{" + "file=" + file + ", format=" + format + '}';
    }
    
}
