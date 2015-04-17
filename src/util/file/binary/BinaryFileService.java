package util.file.binary;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author Dan
 */
public class BinaryFileService implements Closeable {

    BinaryFileReader reader;
    BinaryFileWriter writer;

    public BinaryFileService(BinaryFileReader reader, BinaryFileWriter writer) {
        setReader(reader);
        setWriter(writer);
    }
    
    public final void setReader(BinaryFileReader reader) {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }

    public final void setWriter(BinaryFileWriter writer) {
        if (writer == null) {
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    public int read() throws IOException {
        return reader.read();
    }
    
    public int read(byte[] bytes, int offset, int length)
            throws IOException {
        return reader.read(bytes, offset, length);
    }
    
    public void write(int b) throws IOException {
        writer.write(b);
    }
    
    public void write(byte[] bytes) throws IOException {
        writer.write(bytes);
    }
    
    public void write(byte[] bytes, int offset, int length) throws IOException {
        writer.write(bytes, offset, length);
    }
    
    public void closeReader() throws IOException {
        reader.close();
    }
    
    public void closeWriter() throws IOException {
        writer.close();
    }
    
    @Override
    public void close() throws IOException {
        closeReader();
        closeWriter();
    }
    
}
