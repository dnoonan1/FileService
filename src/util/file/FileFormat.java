package util.file;

import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileFormat
    extends Encoder<List<Record>, String>, Decoder<String, List<Record>> {}
