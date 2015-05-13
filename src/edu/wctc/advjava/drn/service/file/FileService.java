package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * This class is a high-level class that uses the services of low-level
 * {@code FileReaderStrategy}s and {@code FileWriterStrategy}s to read and 
 * write data to and from text-formatted files, respectively.
 * 
 * @author Dan Noonan
 * @see FileServiceStrategy
 */
public class FileService implements FileServiceStrategy<List<Record>> {
    
    private FileReaderStrategy<List<Record>> reader;
    private FileWriterStrategy<List<Record>> writer;

    /**
     * Constructs a new instance of {@code FileService}.
     */
    public FileService() {}
    
    /**
     * Constructs a new instance of {@code FileService} with the specified 
     * reader and writer.
     * 
     * @param reader the FileReaderStrategy
     * @param writer the FileWriterStrategy
     */
    public FileService(
            final FileReaderStrategy<List<Record>> reader,
            final FileWriterStrategy<List<Record>> writer) {
        setReader(reader);
        setWriter(writer);
    }
    
    /**
     * Sets the reader for this {@code FileService}.
     * 
     * @param reader the new reader
     */
    @Override
    public final void setReader(final FileReaderStrategy<List<Record>> reader) {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }

    /**
     * Sets the writer for this {@code FileService}.
     * 
     * @param writer the new writer 
     */
    @Override
    public final void setWriter(final FileWriterStrategy<List<Record>> writer) {
        if (writer == null) {
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    /**
     * Reads the whole file and returns a list of records. 
     * 
     * @return a list of records representing the file data
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    @Override
    public final List<Record> readAll() throws IOException, FileFormatException {
        return reader.read();
    }
    
    /**
     * Writes a list of records to file. 
     * 
     * @param records the list of records to write to file
     * @param append whether to append (true) or overwrite (false)
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    @Override
    public final void writeAll(final List<Record> records, final boolean append)
            throws IOException, FileFormatException {
        writer.write(records, append);
    }
    
    /**
     * Writes a list of records to file, appending to the current file (if it
     * already exists). 
     * 
     * @param records the list of records to write to file
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    @Override
    public final void append(final List<Record> records)
            throws IOException, FileFormatException {
        writer.write(records, FileServiceStrategy.APPEND);
    }
    
    /**
     * Writes a list of records to file, overwriting the current file (if it 
     * already exists). 
     * 
     * @param records the list of records to write to file
     * 
     * @throws IOException if an error occurs reading the file
     * @throws FileFormatException if the file does not match the required
     *    format 
     */
    @Override
    public final void overwrite(final List<Record> records)
            throws IOException, FileFormatException {
        writer.write(records, FileServiceStrategy.OVERWRITE);
    }

    /**
     * Gets the hash code for this object;
     * 
     * @return the hash code for this object 
     */
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.reader);
        hash = 89 * hash + Objects.hashCode(this.writer);
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
    public final boolean equals(Object obj) {
        if (obj instanceof FileService) {
            FileService that = (FileService)obj;
            return this.reader .equals( that.reader )
                && this.writer .equals( that.writer );
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
        return "RecordFileService{" + "reader=" + reader + ", writer=" + writer + '}';
    }
    
}
