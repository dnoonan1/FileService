package util.file;

import util.Record;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileReaderStrategy {
    public List<Record> readAll() throws IOException, ParseException;    
}
