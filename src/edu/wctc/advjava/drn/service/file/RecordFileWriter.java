package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class RecordFileWriter implements FileWriterStrategy<List<Record>> {

    private final File file;
    private final RecordFileFormat format;
    
    public RecordFileWriter(final String filePath, final RecordFileFormat format)
            throws FileNotFoundException {
        this.file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        if (format == null) {
            throw new IllegalArgumentException();
        }
        this.format = format;
    }
    
    @Override
    public final void write(final List<Record> records, final boolean append)
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

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.file);
        hash = 83 * hash + Objects.hashCode(this.format);
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof RecordFileWriter) {
            RecordFileWriter that = (RecordFileWriter)obj;
            return this.file  .equals( that.file )
                && this.format.equals( that.format );
        }
        return false;
    }

    @Override
    public final String toString() {
        return "RecordFileWriter{" + "file=" + file + ", format=" + format + '}';
    }
    
}
