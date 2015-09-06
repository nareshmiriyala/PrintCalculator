package com.papercut;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.helper.PrintCostCalculator;
import com.papercut.model.PrintCostData;
import com.papercut.print.CSVPrintJobReader;
import com.papercut.print.Paper;
import com.papercut.print.PrintJob;
import com.papercut.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * This program takes a list of A4 print jobs and calculates the
 * cost of each job, given the total number of pages, number of colour pages and whether printing is double sided.
 * Created by nareshm on 5/09/2015.
 */
public class PrintCalculatorApp {
    private static final Logger logger = LoggerFactory.getLogger(PrintCalculatorApp.class);
    public static final String CSV_FILE = "src/test/resources/printjobs.csv";

    public static void main(String[] args) {
        System.out.println("Reading print Jobs from file "+CSV_FILE);
        try {
            List<PrintJob> printJobs = CSVPrintJobReader.getInstance().createPrintJobs(CSV_FILE);
            BigDecimal totalCost = BigDecimal.ZERO;
            System.out.println("Job details and the cost of printing");
            for (PrintJob printJob : printJobs) {
                BigDecimal cost = printJob.cost();
                System.out.println("" + printJob + ", Cost of printing $" + Utility.getRoundedValue(cost));
                totalCost = totalCost.add(cost);
            }
            System.out.println("Total cost of all print  $" + Utility.getRoundedValue(totalCost));
        } catch (PrintCalculationException e) {
            logger.error("Error during Print Calculation", e);
        }
    }


}
