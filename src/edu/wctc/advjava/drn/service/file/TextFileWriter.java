package edu.wctc.advjava.drn.service.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    
}
