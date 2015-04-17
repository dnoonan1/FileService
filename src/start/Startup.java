package start;

import util.file.FileFormat;
import util.file.FileService;
import util.file.Record;
import util.file.format.CsvFileFormat;
import util.file.format.IniFileFormat;
import util.file.text.TextFileReader;
import util.file.text.TextFileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public class Startup {

    public static void main(String[] args) throws IOException {
        
        final boolean WITH_HEADER = true;
        final char TAB = '\t';
        final FileFormat CSV_COMMA = new CsvFileFormat();
        final FileFormat CSV_COMMA_WITH_HEADER = new CsvFileFormat(WITH_HEADER);
        final FileFormat CSV_TAB = new CsvFileFormat(TAB);
        final FileFormat INI = new IniFileFormat();
        final char SEPARATOR = File.separatorChar;
        
        String fileFolder = "src" + SEPARATOR + "data" + SEPARATOR;
        String fileInputPath = fileFolder + "spline-data.txt";        
        String fileOutputPath = fileFolder + "spline-data.csv";
        
        FileService fs = new FileService(
                new TextFileReader(fileInputPath, CSV_TAB),
                new TextFileWriter(fileOutputPath, CSV_COMMA)
        );
        
        // Read data from CSV file
        System.out.println("Reading records from CSV...");
        List<Record> records = fs.readAll();
        System.out.println("...Done!");
        
        // Print out records
        System.out.println("\nList of Records:");
        for (Record rec : records) {
            System.out.println(rec);
        }
        
        // Write to different CSV format
        System.out.println("\nWriting records to file...");
        fs.overwrite(records);
        System.out.println("...Done!");
        
        // Switch to INI file format
        fs.setReader(new TextFileReader(fileFolder + "quickres.ini", INI));
        fs.setWriter(new TextFileWriter(fileFolder + "quickres(1).ini", INI));
        
        // Read data from INI file
        System.out.println("\nReading INI...");
        records = fs.readAll();
        
        // Print out records
        System.out.println("\nList of Records:");
        for (Record rec : records) {
            System.out.println(rec);
        }
        
        // Write to file (comments removed)
        System.out.println("\nWriting records to file...");
        fs.overwrite(records);
        System.out.println("...Done!");
        
        // Switch to read CSV file with header
        fs.setReader(new TextFileReader(fileFolder + "contacts.csv", CSV_COMMA_WITH_HEADER));
        
        records = fs.readAll();
        System.out.println("\nList of Records:");
        for (Record rec : records) {
            System.out.println(rec);
        }
        
    }
    
}
