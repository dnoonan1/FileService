package edu.wctc.advjava.drn.service.file;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Dan Noonan
 */
public class TextFileService implements FileService<String> {
    
    private FileReaderStrategy<String> reader;
    private FileWriterStrategy<String> writer;

    public TextFileService() {}
    
    public TextFileService(
            final FileReaderStrategy<String> reader,
            final FileWriterStrategy<String> writer) {
        setReader(reader);
        setWriter(writer);
    }
    
    @Override
    public final void setReader(final FileReaderStrategy<String> reader) {
        if (reader == null) {
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }

    @Override
    public final void setWriter(final FileWriterStrategy<String> writer) {
        if (writer == null) {
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    
    @Override
    public final String read() throws IOException, FileFormatException {
        return reader.read();
    }
    
    @Override
    public final void write(final String data, final boolean append)
            throws IOException, FileFormatException {
        writer.write(data, append);
    }
    
    @Override
    public final void append(final String data)
            throws IOException, FileFormatException {
        writer.write(data, FileService.APPEND);
    }
    
    @Override
    public final void overwrite(final String data)
            throws IOException, FileFormatException {
        writer.write(data, FileService.OVERWRITE);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.reader);
        hash = 11 * hash + Objects.hashCode(this.writer);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof TextFileService) {
            TextFileService that = (TextFileService)obj;
            return this.reader .equals( that.reader )
                && this.writer .equals( that.writer );
        }
        return false;
    }

    @Override
    public final String toString() {
        return "TextFileService{" + "reader=" + reader + ", writer=" + writer + '}';
    }
    
}
