package util.file;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileReaderStrategy {
    public List<Record> readAll() throws IOException;    
}
