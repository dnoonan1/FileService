package fileservice;

import edu.wctc.advjava.drn.service.file.*;
import edu.wctc.advjava.drn.service.file.format.*;
import edu.wctc.advjava.drn.util.Record;
import java.io.*;
import java.text.ParseException;
import java.util.List;


/**
 *
 * @author Dan
 */
public class Startup {

    public static void main(String[] args) throws IOException, ParseException {
        
        final boolean WITH_HEADER = true;
        final char TAB = '\t';
        final RecordFileFormat CSV_COMMA = new CSVFileFormat();
        final RecordFileFormat CSV_WITH_HEADER = new CSVFileFormat(WITH_HEADER);
        final RecordFileFormat CSV_TAB = new CSVFileFormat(TAB);
        final RecordFileFormat INI = new INIFileFormat();
        final char SEPARATOR = File.separatorChar;
        
        String dir = "test" + SEPARATOR + "data" + SEPARATOR;
        String inputFilePath;
        
        RecordFileService fs = new RecordFileService();
        List<Record> records;
        
        inputFilePath = dir + "spline-data.txt";
        
        try {
            fs.setReader(new RecordFileReader(inputFilePath, CSV_TAB));
            fs.setWriter(new RecordFileWriter(dir + "spline-data.csv", CSV_COMMA));
            
            // Read data from CSV file
            System.out.println("Reading records from CSV...");
            records = fs.read();
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
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + inputFilePath);
        } catch (CSVParseException pe) {
            System.out.println("Exception parsing CSV line " + pe.getLineNumber()
                    + " at column " + pe.getErrorOffset()
                    + ": " + pe.getMessage());
        } catch (FileFormatException ffe) {
            System.out.println(ffe.getMessage());
        }
        
        inputFilePath = dir + "sample.ini";
        try {
            // Switch to INI file format
            fs.setReader(new RecordFileReader(inputFilePath, INI));
            fs.setWriter(new RecordFileWriter(dir + "sample(1).ini", INI));
            
            // Read data from INI file
            System.out.println("\nReading INI...");
            records = fs.read();

            // Print out records
            System.out.println("\nList of Records:");
            for (Record rec : records) {
                System.out.println(rec);
            }

            // Write to file (comments removed)
            System.out.println("\nWriting records to file...");
            fs.overwrite(records);
            System.out.println("...Done!");

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + inputFilePath);
        } catch (INIParseException pe) {
            System.out.println("Exception parsing INI line " + pe.getLineNumber()
                        + " at column " + pe.getErrorOffset()
                        + ": " + pe.getMessage());
        } catch (FileFormatException ffe) {
            System.out.println(ffe.getMessage());
        }
        
        inputFilePath = dir + "contacts.csv";
        try {
            // Switch to read CSV file with header
            fs.setReader(new RecordFileReader(inputFilePath, CSV_WITH_HEADER));

            records = fs.read();
            System.out.println("\nList of Records:");
            for (Record rec : records) {
                System.out.println(rec);
            }
            
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + inputFilePath);
        } catch (CSVParseException pe) {
            System.out.println("Exception parsing CSV line " + pe.getLineNumber()
                        + " at column " + pe.getErrorOffset()
                        + ": " + pe.getMessage());  
        } catch (FileFormatException ffe) {
            System.out.println(ffe.getMessage());
        }
        
    } 
    
}
