package edu.wctc.advjava.drn.service.file;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class BinaryFileService implements Closeable {

    private BinaryFileReader reader;
    private BinaryFileWriter writer;

    public BinaryFileService(final BinaryFileReader reader, final BinaryFileWriter writer) {
        setReader(reader);
        setWriter(writer);
    }
    
    public final void setReader(final BinaryFileReader reader) {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }

    public final void setWriter(final BinaryFileWriter writer) {
        if (writer == null) {
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    public final int read() throws IOException {
        return reader.read();
    }
    
    public final int read(final byte[] bytes, final int offset, final int length)
            throws IOException {
        return reader.read(bytes, offset, length);
    }
    
    public final void write(final int b) throws IOException {
        writer.write(b);
    }
    
    public final void write(final byte[] bytes) throws IOException {
        writer.write(bytes);
    }
    
    public final void write(final byte[] bytes, final int offset, final int length) throws IOException {
        writer.write(bytes, offset, length);
    }
    
    public final void closeReader() throws IOException {
        reader.close();
    }
    
    public final void closeWriter() throws IOException {
        writer.close();
    }
    
    @Override
    public final void close() throws IOException {
        closeReader();
        closeWriter();
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.reader);
        hash = 79 * hash + Objects.hashCode(this.writer);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof BinaryFileService) {
            BinaryFileService that = (BinaryFileService)obj;
            return this.reader .equals( that.reader )
                && this.writer .equals( that.writer );
        }
        return false;
    }

    @Override
    public final String toString() {
        return "BinaryFileService{" + "reader=" + reader + ", writer=" + writer + '}';
    }
    
}
