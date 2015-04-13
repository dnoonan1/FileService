package drn.util.file.binary;

import java.io.*;

/**
 *
 * @author Dan
 */
public class BinaryFileReader  {
    
    BufferedInputStream in;
    
    public BinaryFileReader(String filePath) throws FileNotFoundException {
        in = new BufferedInputStream(
                new FileInputStream(
                    new File(filePath))
        );
    }
    
    public BinaryFileReader(String filePath, int size)
            throws FileNotFoundException {
        in = new BufferedInputStream(
                new FileInputStream(
                    new File(filePath)),
                size
        );
    }
    
    public int read() throws IOException {
        return in.read();
    }
    
    public int read(byte[] bytes, int offset, int length) throws IOException {
        return in.read(bytes, offset, length);
    }
    
    public void close() throws IOException {
        in.close();
    }
    
}
