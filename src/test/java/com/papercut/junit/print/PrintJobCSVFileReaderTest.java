package com.papercut.junit.print;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.PrintJobCSVFileReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Unit test for PrintJobCSVFileReader class
 * Created by nareshm on 5/09/2015.
 */
public class PrintJobCSVFileReaderTest {

    private String csvFile;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull("Instance shouldn't be null", PrintJobCSVFileReader.getInstance());
    }

    @Test
    public void testCreatePrintJobsInvalidInputFile() throws Exception {
        expectedEx.expect(PrintCalculationException.class);
        expectedEx.expectMessage("CSV FileName is invalid");
        PrintJobCSVFileReader.getInstance().createPrintJobs(csvFile);
    }

    @Test
    public void testCreatePrintJobsFileNotFound() throws Exception {
        csvFile = "testname";
        expectedEx.expect(PrintCalculationException.class);
        expectedEx.expectMessage("Not able to find the file");
        PrintJobCSVFileReader.getInstance().createPrintJobs(csvFile);
    }

}