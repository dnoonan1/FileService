package util.file.text;

import util.file.FileFormat;
import util.file.FileReaderStrategy;
import util.file.Record;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public class TextFileReader implements FileReaderStrategy {

    private File file;
    private FileFormat format;
    
    public TextFileReader(String filePath, FileFormat format) {
        this.file = new File(filePath);
        setFormat(format);
    }
    
    public final void setFormat(FileFormat format) {
        if (format == null) {
            throw new IllegalArgumentException();
        }
        this.format = format;
    }
    
    @Override
    public final List<Record> readAll() throws IOException {
        
        final String NEWLINE = String.format("%n");
        
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String data = "";
            String line;
            while ((line = in.readLine()) != null) {
                data += line + NEWLINE;
            }
            return format.decode(data);
        }
        
    }
    
}
