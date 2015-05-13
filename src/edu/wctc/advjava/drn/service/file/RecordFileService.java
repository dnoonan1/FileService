package edu.wctc.advjava.drn.service.file;

import edu.wctc.advjava.drn.util.Record;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dan
 */
public class RecordFileService implements FileServiceStrategy<List<Record>> {
    
    private FileReaderStrategy<List<Record>> reader;
    private FileWriterStrategy<List<Record>> writer;

    public RecordFileService() {}
    
    public RecordFileService(
            final FileReaderStrategy<List<Record>> reader,
            final FileWriterStrategy<List<Record>> writer) {
        setReader(reader);
        setWriter(writer);
    }
    
    @Override
    public final void setReader(final FileReaderStrategy<List<Record>> reader) {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }

    @Override
    public final void setWriter(final FileWriterStrategy<List<Record>> writer) {
        if (writer == null) {
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    @Override
    public final List<Record> read() throws IOException, FileFormatException {
        return reader.read();
    }
    
    @Override
    public final void write(final List<Record> records, final boolean append)
            throws IOException, FileFormatException {
        writer.write(records, append);
    }
    
    @Override
    public final void append(final List<Record> records)
            throws IOException, FileFormatException {
        writer.write(records, FileServiceStrategy.APPEND);
    }
    
    @Override
    public final void overwrite(final List<Record> records)
            throws IOException, FileFormatException {
        writer.write(records, FileServiceStrategy.OVERWRITE);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.reader);
        hash = 89 * hash + Objects.hashCode(this.writer);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof RecordFileService) {
            RecordFileService that = (RecordFileService)obj;
            return this.reader .equals( that.reader )
                && this.writer .equals( that.writer );
        }
        return false;
    }

    @Override
    public final String toString() {
        return "RecordFileService{" + "reader=" + reader + ", writer=" + writer + '}';
    }
    
}
