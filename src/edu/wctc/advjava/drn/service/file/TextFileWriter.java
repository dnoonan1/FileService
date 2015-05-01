package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public class TextFileWriter implements FileWriterStrategy {

    private File file;
    private FileFormat format;
    
    public TextFileWriter(String filePath, FileFormat format) {
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
    public void writeAll(List<Record> records, boolean append)
            throws IOException, FileFormatException {
    
        try (BufferedWriter out =
                new BufferedWriter(
                    new FileWriter(file, append))) {
            out.write(format.encode(records));
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            throw e;
        }
        
    }
    
}
