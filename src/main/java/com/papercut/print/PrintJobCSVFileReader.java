package com.papercut.print;

import com.papercut.exceptions.InvalidInputException;
import com.papercut.exceptions.PrintCalculationException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to read print jobs from CSV file.
 * Created by nareshm on 4/09/2015.
 */
public class PrintJobCSVFileReader {
    private static final Logger logger = LoggerFactory.getLogger(PrintJobCSVFileReader.class);
    private volatile static PrintJobCSVFileReader uniqueInstance;

    /**
     * Constructor should be private for a singleton class
     */
    private PrintJobCSVFileReader() {

    }

    /**
     * Create a unique instance of the PrintJobCSVFileReader.
     *
     * @return a single unique instance.
     */
    public static PrintJobCSVFileReader getInstance() {
        if (uniqueInstance == null) {
            synchronized (PrintJobCSVFileReader.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new PrintJobCSVFileReader();
                }
            }
        }
        return uniqueInstance;
    }
    public List<PrintJob> createPrintJobs(String fileName) throws PrintCalculationException {
        if(fileName==null||fileName.isEmpty()){
            logger.error("FileName is invalid {}",fileName);
            throw  new PrintCalculationException("Error reading the fileName");
        }
        Reader in = null;
        List<PrintJob> printJobs=null;
        try {

            printJobs=new ArrayList<>();
            in = new FileReader(fileName);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            long jobId=1;
            for (CSVRecord record : records) {
                //assign 0 when empty value in csv
                int totalNumberOfPages = Integer.parseInt(record.get(0).trim().isEmpty()?"0":record.get(0).trim());
                //0 when empty value
                int noOfColorPages = Integer.parseInt(record.get(1).trim().isEmpty()?"0":record.get(1).trim());
                //default value of doubleSided is false if nothing provided in the csv file
                boolean doubleSided= Boolean.parseBoolean(record.get(2).trim().isEmpty()?"false":record.get(2).trim());
                //build the A4 size printer job
                printJobs.add(new SchoolPrintJob.SchoolPrintJobBuilder(jobId++).withTotalPrintPages(totalNumberOfPages).withPaperSize(Paper.SIZE.A4).withNoOfColorPages(noOfColorPages).isDoubleSidedPrint(doubleSided).build());
            }
        } catch (Exception e) {
           logger.error("Exception processing the csv file",e);
            throw  new PrintCalculationException("Error processing csv file");
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("Exception closing the reader", e);
                }
            }
        }
        return printJobs;
    }
    }


