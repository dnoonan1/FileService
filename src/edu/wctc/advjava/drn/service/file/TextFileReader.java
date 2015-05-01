package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.service.file.FileFormat;
import edu.wctc.advjava.drn.service.file.FileReaderStrategy;
import edu.wctc.advjava.drn.util.Record;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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
    public final List<Record> readAll() throws IOException, FileFormatException {
        
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
