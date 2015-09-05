package com.papercut.regression;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.PrintJob;
import com.papercut.print.PrintJobCSVFileReader;
import com.papercut.util.Utility;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

/**
 * Print Calculator App Test.
 * Created by nareshm on 4/09/2015.
 */
public class PrintCalculator {
    @Test
    public void testApp() throws PrintCalculationException {
        List<PrintJob> printJobs = PrintJobCSVFileReader.getInstance().createPrintJobs("src/test/resources/printjobs.csv");
        BigDecimal totalCost = BigDecimal.ZERO;
        for (PrintJob printJob : printJobs) {
            BigDecimal cost = printJob.cost();
            System.out.println("Job details:" + printJob + ", cost of print jobs:" + Utility.getRoundedValue(cost));
            totalCost = totalCost.add(cost);
        }
        System.out.println("Total cost of all print jobs:" + Utility.getRoundedValue(totalCost));

    }
}
