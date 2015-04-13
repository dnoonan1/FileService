package drn.util.file;

import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileFormat {

    public String encodeAll(List<Record> records);
    public List<Record> decodeAll(String data);
    
}
