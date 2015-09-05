package com.papercut.print;

import com.papercut.exceptions.PrintCalculationException;

import java.util.List;

/**
 * Interface for reading a CSV File and creating print jobs.
 * Created by nareshm on 5/09/2015.
 */
public interface CSVFileReader {
    List<PrintJob> createPrintJobs(String csvFile) throws PrintCalculationException;
}
