package util.file;

import util.Record;
import util.Encoder;
import util.Decoder;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Dan
 */
public interface FileFormat
    extends Encoder<List<Record>, String>, Decoder<String, List<Record>> {
    List<Record> decode(String data) throws ParseException;
}
