package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileWriterStrategy {    
    public void writeAll(List<Record> records, boolean append)
            throws IOException, FileFormatException;   
}
