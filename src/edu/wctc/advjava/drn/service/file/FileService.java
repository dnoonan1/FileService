package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.IOException;
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

    public FileService() {}
    
    public FileService(FileReaderStrategy reader, FileWriterStrategy writer) {
        setReader(reader);
        setWriter(writer);
    }
    
    public final void setReader(FileReaderStrategy reader) {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }

    public final void setWriter(FileWriterStrategy writer) {
        if (writer == null) {
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    public final List<Record> readAll() throws IOException, FileFormatException {
        return reader.readAll();
    }
    
    public final void writeAll(List<Record> records, boolean append)
            throws IOException, FileFormatException {
        writer.writeAll(records, append);
    }
    
    public final void append(List<Record> records)
            throws IOException, FileFormatException {
        writer.writeAll(records, APPEND);
    }
    
    public final void overwrite(List<Record> records)
            throws IOException, FileFormatException {
        writer.writeAll(records, OVERWRITE);
    }
    
}
