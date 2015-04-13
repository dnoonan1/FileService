package drn.util.file.binary;

import java.io.*;

/**
 *
 * @author Dan
 */
public class BinaryFileWriter {

    BufferedOutputStream out;
    
    public BinaryFileWriter(String filePath) throws FileNotFoundException {
        out = new BufferedOutputStream(
                new FileOutputStream(
                    new File(filePath))
        );
    }
    
    public BinaryFileWriter(String filePath, int size)
            throws FileNotFoundException {
        out = new BufferedOutputStream(
                new FileOutputStream(
                    new File(filePath)),
                size
        );
    }
    
    public void write(int b) throws IOException {
        out.write(b);
    }
    
    public void write(byte[] bytes) throws IOException {
        out.write(bytes);
    }
    
    public void write(byte[] bytes, int offset, int length) throws IOException {
        out.write(bytes, offset, length);
    }
    
    public void close() throws IOException {
        out.close();
    }
    
}
