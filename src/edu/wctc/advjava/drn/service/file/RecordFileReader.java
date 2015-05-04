package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class RecordFileReader implements FileReaderStrategy<List<Record>> {
    
    private final File file;
    private final RecordFileFormat format;
    
    public RecordFileReader(final String filePath, final RecordFileFormat format)
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
    public final List<Record> read()
            throws IOException, FileFormatException {

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String data = FileFormat.EMPTY_STRING;
            String line;
            while ((line = in.readLine()) != null) {
                data += line + FileFormat.Char.CR;
            }
            return format.decode(data);
        }
        
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.file);
        hash = 59 * hash + Objects.hashCode(this.format);
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof RecordFileReader) {
            final RecordFileReader that = (RecordFileReader)obj;
            return this.file  .equals( that.file )
                && this.format.equals( that.format );
        }
        return false;
    }

    @Override
    public final String toString() {
        return "RecordFileReader{" + "file=" + file + ", format=" + format + '}';
    }
    
}
