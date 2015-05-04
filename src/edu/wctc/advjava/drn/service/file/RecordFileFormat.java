package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface RecordFileFormat extends FileFormat<List<Record>> {
    @Override
    String encode(List<Record> records) throws FileFormatException;
    @Override
    List<Record> decode(String data) throws FileFormatException;    
}
