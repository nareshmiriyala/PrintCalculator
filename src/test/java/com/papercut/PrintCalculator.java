package com.papercut;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.PrintJob;
import com.papercut.print.PrintJobCSVFileReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Print Calculator App Test.
 * Created by nareshm on 4/09/2015.
 */
public class PrintCalculator {
    public static void main(String[] args) throws PrintCalculationException {
        List<PrintJob> printJobs = PrintJobCSVFileReader.getInstance().createPrintJobs("src/test/resources/printjobs.csv");
        BigDecimal totalCost = BigDecimal.ZERO;
        for(PrintJob printJob:printJobs){
            BigDecimal cost = printJob.cost();
            System.out.println("Job details:"+printJob+", cost of print jobs:"+ cost.setScale(2, RoundingMode.CEILING));
            totalCost=totalCost.add(cost);
        }
        System.out.println("Total cost of all print jobs:"+totalCost.setScale(2, RoundingMode.CEILING));

    }
}
