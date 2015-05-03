package edu.wctc.advjava.drn.service.file;

import java.io.*;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class BinaryFileWriter implements Closeable {

    private final BufferedOutputStream out;
    
    public BinaryFileWriter(final String filePath)
            throws FileNotFoundException {
        out = new BufferedOutputStream(
                new FileOutputStream(
                    new File(filePath))
        );
    }
    
    public BinaryFileWriter(final String filePath, final int size)
            throws FileNotFoundException {
        out = new BufferedOutputStream(
                new FileOutputStream(
                    new File(filePath)),
                size
        );
    }
    
    public final void write(final int b) throws IOException {
        out.write(b);
    }
    
    public final void write(final byte[] bytes) throws IOException {
        out.write(bytes);
    }
    
    public final void write(final byte[] bytes, final int offset, final int length) throws IOException {
        out.write(bytes, offset, length);
    }
    
    @Override
    public final void close() throws IOException {
        out.close();
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.out);
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof BinaryFileWriter) {
            final BinaryFileWriter that = (BinaryFileWriter)obj;
            return this.out.equals(that.out);
        }
        return false;
    }

    @Override
    public final String toString() {
        return "BinaryFileWriter{" + "out=" + out + '}';
    }
    
}
