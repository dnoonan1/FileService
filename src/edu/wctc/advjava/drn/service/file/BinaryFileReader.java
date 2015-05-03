package edu.wctc.advjava.drn.service.file;

import java.io.*;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class BinaryFileReader implements Closeable  {
    
    private final BufferedInputStream in;
    
    public BinaryFileReader(final String filePath) throws FileNotFoundException {
        in = new BufferedInputStream(
                new FileInputStream(
                    new File(filePath))
        );
    }
    
    public BinaryFileReader(final String filePath, final int size)
            throws FileNotFoundException {
        in = new BufferedInputStream(
                new FileInputStream(
                    new File(filePath)),
                size
        );
    }
    
    public final int read() throws IOException {
        return in.read();
    }
    
    public final int read(final byte[] bytes, final int offset, final int length) throws IOException {
        return in.read(bytes, offset, length);
    }
    
    @Override
    public final void close() throws IOException {
        in.close();
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.in);
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof BinaryFileReader) {
            final BinaryFileReader that = (BinaryFileReader)obj;
            return this.in.equals(that.in);
        }
        return false;
    }

    @Override
    public final String toString() {
        return "BinaryFileReader{" + "in=" + in + '}';
    }
    
}
