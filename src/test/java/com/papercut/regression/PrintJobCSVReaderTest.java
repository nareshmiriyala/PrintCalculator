package com.papercut.regression;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.CSVPrintJobReader;
import com.papercut.print.PrintJob;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for CSVPrintJobReader class.
 * Created by nareshm on 5/09/2015.
 */
public class PrintJobCSVReaderTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testCreatePrintJobs() throws Exception {
        List<PrintJob> printJobs = CSVPrintJobReader.getInstance().createPrintJobs("src/test/resources/printjobs.csv");
        assertEquals("Size of the printJobs list should be 4", 4, printJobs.size());
    }

    @Test
    public void testCreatePrintJobsEmptyCSVFile() throws Exception {
        List<PrintJob> printJobs = CSVPrintJobReader.getInstance().createPrintJobs("src/test/resources/printjobemptyfile.csv");
        assertEquals("Size of the printJobs list should be 0", 0, printJobs.size());
    }

    @Test
    public void testCreatePrintJobsInvalidCSVFile() throws Exception {
        expectedEx.expect(PrintCalculationException.class);
        expectedEx.expectMessage("Error reading the csv file");
        CSVPrintJobReader.getInstance().createPrintJobs("src/test/resources/printjobsinvalidcsv.csv");
    }

    @Test
    public void testCreatePrintJobsInvalidTotalPages() throws Exception {
        expectedEx.expect(PrintCalculationException.class);
        expectedEx.expectMessage("CSV record is invalid");
        CSVPrintJobReader.getInstance().createPrintJobs("src/test/resources/printjobsInvalidTotalPages.csv");
    }
}
