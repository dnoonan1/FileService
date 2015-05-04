package edu.wctc.advjava.drn.service.file;

import java.io.*;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class TextFileReader implements FileReaderStrategy<String> {
    
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
            String data = FileFormat.EMPTY_STRING;
            String line;
            while ((line = in.readLine()) != null) {
                data += line + FileFormat.Char.CR;
            }
            return data;
        }     
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.file);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextFileReader) {
            final TextFileReader that = (TextFileReader)obj;
            return this.file.equals(that.file);
        }
        return false;
    }

    @Override
    public String toString() {
        return "TextFileReader{" + "file=" + file + '}';
    }
   
}
