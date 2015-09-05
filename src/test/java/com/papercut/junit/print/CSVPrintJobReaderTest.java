package com.papercut.junit.print;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.CSVPrintJobReader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Unit test for CSVPrintJobReader class
 * Created by nareshm on 5/09/2015.
 */
public class CSVPrintJobReaderTest {

    private String csvFile;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull("Instance shouldn't be null", CSVPrintJobReader.getInstance());
    }

    @Test
    public void testCreatePrintJobsInvalidInputFile() throws Exception {
        expectedEx.expect(PrintCalculationException.class);
        expectedEx.expectMessage("CSV FileName is invalid");
        CSVPrintJobReader.getInstance().createPrintJobs(csvFile);
    }

    @Test
    public void testCreatePrintJobsFileNotFound() throws Exception {
        csvFile = "testname";
        expectedEx.expect(PrintCalculationException.class);
        expectedEx.expectMessage("Not able to find the file");
        CSVPrintJobReader.getInstance().createPrintJobs(csvFile);
    }

}