package com.papercut.regression;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.CSVPrintJobReader;
import com.papercut.print.PrintJob;
import com.papercut.util.Utility;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Print Calculator App Test.
 * Created by nareshm on 4/09/2015.
 */
public class PrintCalculator {
    @Test
    public void testApp() throws PrintCalculationException {
        List<PrintJob> printJobs = CSVPrintJobReader.getInstance().createPrintJobs("src/test/resources/printjobs.csv");
        BigDecimal totalCost = BigDecimal.ZERO;
        for (PrintJob printJob : printJobs) {
            BigDecimal cost = printJob.cost();
            System.out.println("" + printJob + ", cost of print:" + Utility.getRoundedValue(cost));
            totalCost = totalCost.add(cost);
        }
        System.out.println("Total cost of all print jobs:" + Utility.getRoundedValue(totalCost));
        assertEquals("Total cost of all jobs", Utility.getRoundedValue(BigDecimal.valueOf(64.10)), Utility.getRoundedValue(totalCost));
    }
}
