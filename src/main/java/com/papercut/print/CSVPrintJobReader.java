package com.papercut.print;

import com.papercut.exceptions.InvalidInputException;
import com.papercut.exceptions.PrintCalculationException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to read print jobs from CSV file.
 * Its a Singleton class.
 * Created by nareshm on 4/09/2015.
 */
public class CSVPrintJobReader implements PrintJobReader {
    private static final Logger logger = LoggerFactory.getLogger(CSVPrintJobReader.class);
    private volatile static PrintJobReader uniqueInstance;

    /**
     * Constructor should be private for a singleton class
     */
    private CSVPrintJobReader() {

    }

    /**
     * Create a unique instance of the CSVPrintJobReader.
     *
     * @return a single unique instance.
     */
    public static PrintJobReader getInstance() {
        if (uniqueInstance == null) {
            synchronized (CSVPrintJobReader.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new CSVPrintJobReader();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public List<PrintJob> createPrintJobs(String csvFile) throws PrintCalculationException {
        if (csvFile == null || csvFile.isEmpty()) {
            logger.error("FileName is invalid {}", csvFile);
            throw new PrintCalculationException("CSV FileName is invalid");
        }
        Reader in = null;
        List<PrintJob> printJobs = null;
        try {
            in = new FileReader(csvFile);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            long jobId = 1;
            printJobs = new ArrayList<>();
            for (CSVRecord record : records) {
                //assign 0 when empty value in csv
                int totalNumberOfPages = getIntValueFromCSV(record.get(0), jobId);

                //0 when empty value
                int noOfColorPages = getIntValueFromCSV(record.get(1), jobId);
                //default value of doubleSided is false if nothing provided in the csv file
                boolean doubleSided = Boolean.parseBoolean(record.get(2).trim().isEmpty() ? "false" : record.get(2).trim());
                //build the A4 size printer job
                printJobs.add(new SchoolPrintJob.SchoolPrintJobBuilder(jobId).withJobName("A4_PRINT_JOB_"+jobId).withTotalPrintPages(totalNumberOfPages).withPaperSize(Paper.SIZE.A4).withNoOfColorPages(noOfColorPages).isDoubleSidedPrint(doubleSided).build());
                //increment jobId
                jobId++;
            }
        } catch (FileNotFoundException e) {
            logger.error("Not able to find the file {}", csvFile);
            throw new PrintCalculationException("Not able to find the file");
        } catch (InvalidInputException e) {
            logger.error("Input value to SchoolPrintJob is Invalid");
            throw new PrintCalculationException("Input value to SchoolPrintJob is Invalid");
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            logger.error("Error reading the csv file {}", csvFile);
            throw new PrintCalculationException("Error reading the csv file");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("Exception closing the reader", e);
                }
            }
        }
        return printJobs;
    }

    private static int getIntValueFromCSV(String value, long i) throws PrintCalculationException {
        if (value == null) {
            logger.error("CSV Record at line number {} is invalid", i);
            throw new PrintCalculationException("CSV record is invalid");
        }
        //trim the value
        value = value.trim();
        //return default value as 0 when empty
        if (value.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.error("CSV Record value {} at line number {} is not a number", value, i);
        }
        throw new PrintCalculationException("CSV record is invalid");
    }
}


