package com.papercut.print;

import com.papercut.exceptions.PrintCalculationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for PrintJobCSVFileReader class
 * Created by nareshm on 5/09/2015.
 */
@RunWith(PowerMockRunner.class)
public class PrintJobCSVFileReaderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull("Instance shouldn't be null", PrintJobCSVFileReader.getInstance());
    }

    @Test
    public void testCreatePrintJobs() throws Exception {
        List<PrintJob> printJobs = PrintJobCSVFileReader.getInstance().createPrintJobs("src/test/resources/printjobs.csv");
        assertEquals("Size of the printJobs list should be 4", 4, printJobs.size());
    }

    @Test(expected = PrintCalculationException.class)
    public  void testCreatePrintJobsInvalidInputFile() throws Exception{
        PrintJobCSVFileReader.getInstance().createPrintJobs(null);
    }

    @Test
    public void testCreatePrintJobsEmptyCSVFile() throws Exception {
        List<PrintJob> printJobs = PrintJobCSVFileReader.getInstance().createPrintJobs("src/test/resources/printjobemptyfile.csv");
        assertEquals("Size of the printJobs list should be 0", 0, printJobs.size());
    }
    @Test(expected = PrintCalculationException.class)
    public void testCreatePrintJobsInvalidCSVFile() throws Exception {
        PrintJobCSVFileReader.getInstance().createPrintJobs("src/test/resources/printjobsinvalidcsv.csv");
    }
}