package edu.wctc.advjava.drn.service.file;

import java.io.*;

/**
 *
 * @author Dan
 */
public class TextFileReader implements FileReaderStrategy<String> {

    private static final String NEWLINE = String.format("%n");
    
    private final File file;
    
    public TextFileReader(final String filePath)
            throws FileNotFoundException {
        this.file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
    }
    
    @Override
    public final String read() throws IOException, FileFormatException {

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String data = FileService.EMPTY_STRING;
            String line;
            while ((line = in.readLine()) != null) {
                data += line + NEWLINE;
            }
            return data;
        }
        
    }
    
}
