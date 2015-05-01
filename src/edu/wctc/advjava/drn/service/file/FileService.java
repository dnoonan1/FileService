package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Dan
 */
public class FileService {
    
    private static final boolean APPEND = true;
    private static final boolean OVERWRITE = false;
    
    private FileReaderStrategy reader;
    private FileWriterStrategy writer;

    public FileService(FileReaderStrategy reader, FileWriterStrategy writer) {
        this.reader = reader;
        this.writer = writer;
    }
    
    public void setReader(FileReaderStrategy reader) {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }

    public void setWriter(FileWriterStrategy writer) {
        if (writer == null) {
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    public List<Record> readAll() throws IOException, FileFormatException {
        return reader.readAll();
    }
    
    public void writeAll(List<Record> records, boolean append)
            throws IOException, FileFormatException {
        writer.writeAll(records, append);
    }
    
    public void append(List<Record> records)
            throws IOException, FileFormatException {
        writer.writeAll(records, APPEND);
    }
    
    public void overwrite(List<Record> records)
            throws IOException, FileFormatException {
        writer.writeAll(records, OVERWRITE);
    }
    
}
