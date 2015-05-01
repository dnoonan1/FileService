package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileReaderStrategy {
    public List<Record> readAll() throws IOException, FileFormatException;    
}
