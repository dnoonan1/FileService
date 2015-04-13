package drn.util.file;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileWriterStrategy {

    public static final boolean APPEND = true;
    public static final boolean OVERWRITE = false;
    
    public void writeAll(List<Record> records, boolean append)
            throws IOException;
    
    public default void append(List<Record> records) throws IOException {
        writeAll(records, APPEND);
    }
    
    public default void overwrite(List<Record> records) throws IOException {
        writeAll(records, OVERWRITE);
    }
    
}
