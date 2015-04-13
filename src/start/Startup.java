package start;

import drn.util.file.FileFormat;
import drn.util.file.FileService;
import drn.util.file.Record;
import drn.util.file.format.CsvFileFormat;
import drn.util.file.format.IniFileFormat;
import drn.util.file.text.TextFileReader;
import drn.util.file.text.TextFileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dan
 */
public class Startup {

    public static void main(String[] args) throws IOException {
                
        final String TAB = "\t";
        final FileFormat CSV_COMMA = new CsvFileFormat();
        final FileFormat CSV_TAB = new CsvFileFormat(TAB);
        final FileFormat INI = new IniFileFormat();
        char SEPARATOR = File.separatorChar;
        
        String fileFolder = "src" + SEPARATOR + "data" + SEPARATOR;
        String fileInputPath = fileFolder + "spline-data.txt";        
        String fileOutputPath = fileFolder + "spline-data.csv";
        
        FileService fs = new FileService(
                new TextFileReader(fileInputPath, CSV_TAB),
                new TextFileWriter(fileOutputPath, CSV_COMMA)
        );
        
        System.out.println("Reading records from CSV...");
        List<Record> records = fs.readAll();
        System.out.println("...Done!");
        
        System.out.println("\nList of Records:");
        for (Record rec : records) {
            System.out.println(rec);
        }
        
        System.out.println("\nWriting records to file...");
        fs.overwrite(records);
        System.out.println("...Done!");
        
        fs.setReader(new TextFileReader(fileFolder + "quickres.ini", INI));
        fs.setWriter(new TextFileWriter(fileFolder + "quickres(1).ini", INI));
        
        System.out.println("\nReading INI...");
        records = fs.readAll();
        System.out.println("\nList of Records:");
        for (Record rec : records) {
            System.out.println(rec);
        }
        System.out.println("\nWriting records to file...");
        fs.overwrite(records);
        System.out.println("...Done!");
        
    }
    
}
