package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.*;
import java.util.List;

/**
 *
 * @author Dan
 */
public class TextFileReader implements FileReaderStrategy {

    private static final String NEWLINE = String.format("%n");
    
    private final File file;
    private FileFormat format;
    
    public TextFileReader(String filePath, FileFormat format)
            throws FileNotFoundException {
        this.file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        setFormat(format);
    }
    
    public final void setFormat(FileFormat format) {
        if (format == null) {
            throw new IllegalArgumentException();
        }
        this.format = format;
    }
    
    @Override
    public final List<Record> readAll()
            throws IOException, FileFormatException {

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
