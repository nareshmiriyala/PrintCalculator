package com.papercut.helper;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.model.PrintCostData;
import com.papercut.print.Paper;
import com.papercut.print.PrintJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Helper class to calculate the total cost of printing a job.
 * Its a Singleton class.
 * Created by nareshm on 4/09/2015.
 */
public class PrintCostCalculator implements CostCalculator {
    private static final Logger logger = LoggerFactory.getLogger(PrintCostCalculator.class);
    private volatile static PrintCostCalculator uniqueInstance;
    private final static Set<PrintCostData> costDataSet = new HashSet<>();

    /**
     * Constructor should be private for a singleton class
     */
    private PrintCostCalculator() {

    }

    /**
     * Create a unique instance of the PrintCostCalculator and initialise the cost data for printing.
     *
     * @return a single unique instance.
     */
    public static CostCalculator getInstance() {
        if (uniqueInstance == null) {
            synchronized (PrintCostCalculator.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new PrintCostCalculator();
                    //initialise cost data.
                    uniqueInstance.initialisePrintCostData();
                }
            }
        }
        return uniqueInstance;
    }

    /**
     * Initialise cost data for printing.
     */
    private void initialisePrintCostData() {
        insertPrintCostData(new PrintCostData(Paper.SIZE.A4, Paper.SIDE.SINGLE_SIDED, BigDecimal.valueOf(.15), BigDecimal.valueOf(.25)));
        insertPrintCostData(new PrintCostData(Paper.SIZE.A4, Paper.SIDE.DOUBLE_SIDED, BigDecimal.valueOf(.10), BigDecimal.valueOf(.20)));
    }

    /**
     * Insert print cost data to the costDataSet.
     *
     * @param printCostData
     */
    @Override
    public void insertPrintCostData(PrintCostData printCostData) {
        costDataSet.add(printCostData);
    }

    /**
     * Calculate the total cost of the printing
     *
     * @param printJob- input the printJob containing the required values for cost calculation
     * @return cost of printing the job.
     */
    public BigDecimal calculateCost(PrintJob printJob) throws PrintCalculationException {
        if (printJob == null) {
            logger.error("PrintJob can't be null");
            throw new PrintCalculationException("PrintJob can't be null");
        }
        if (printJob.getPaperSize() == null) {
            logger.error("PrintJob paper size can't be null");
            throw new PrintCalculationException("PrintJob paper size can't be null");
        }
        int colorPages = printJob.getNoOfColorPages();
        int blackAndWhitePages = printJob.getTotalNumberOfPages() - colorPages;
        logger.debug(" Started calculating the cost of the printJob {}", printJob);
        PrintCostData printJobCostData = new PrintCostData(printJob.getPaperSize(), printJob.isDoubleSided() ? Paper.SIDE.DOUBLE_SIDED : Paper.SIDE.SINGLE_SIDED);
        if (!costDataSet.contains(printJobCostData)) {
            logger.error("Can't find any cost rule to apply for paper size {} ", printJob.getPaperSize());
            throw new PrintCalculationException("No valid rule to calculate cost");
        }
        BigDecimal finalCost = BigDecimal.ZERO;
        for (PrintCostData rule : costDataSet) {
            if (rule != null && rule.equals(printJobCostData)) {
                logger.debug("Applying print cost rule {}", rule);
                finalCost = finalCost.add(BigDecimal.valueOf(blackAndWhitePages).multiply(rule.getBlackAndWhitePaperCost()).
                        add(BigDecimal.valueOf(colorPages).multiply(rule.getColorPaperCost())));
            }
        }
        return finalCost;
    }

}
