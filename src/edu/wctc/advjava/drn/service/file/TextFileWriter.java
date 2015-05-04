package edu.wctc.advjava.drn.service.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class TextFileWriter implements FileWriterStrategy<String> {

    private File file;
    
    public TextFileWriter(String filePath) {
        this.file = new File(filePath);
    }
    
    @Override
    public void write(String data, boolean append)
            throws IOException, FileFormatException {
    
        try (BufferedWriter out =
                new BufferedWriter(
                    new FileWriter(file, append))) {
            out.write(data);
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            throw e;
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.file);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextFileWriter) {
            final TextFileWriter that = (TextFileWriter)obj;
            return this.file.equals(that.file);
        }
        return false;
    }

    @Override
    public String toString() {
        return "TextFileWriter{" + "file=" + file + '}';
    }
    
}
