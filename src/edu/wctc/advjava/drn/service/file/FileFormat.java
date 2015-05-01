package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileFormat {
    String encode(List<Record> data) throws FileFormatException;
    List<Record> decode(String data) throws FileFormatException;    
}
