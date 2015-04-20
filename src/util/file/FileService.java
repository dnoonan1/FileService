package util.file;

import util.Record;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Dan
 */
public class FileService {
    
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
    
    public List<Record> readAll() throws IOException, ParseException {
        return reader.readAll();
    }
    
    public void writeAll(List<Record> records, boolean append)
            throws IOException {
        writer.writeAll(records, append);
    }
    
    public void append(List<Record> records)
            throws IOException {
        writer.append(records);
    }
    
    public void overwrite(List<Record> records)
            throws IOException {
        writer.overwrite(records);
    }
    
}
