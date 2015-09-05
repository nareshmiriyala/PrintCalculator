package com.papercut.helper;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.Paper;
import com.papercut.print.PrintJob;
import com.papercut.print.SchoolPrintJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Helper class to calculate the total cost of printing a job.
 * Its a Singleton class.
 * Created by nareshm on 4/09/2015.
 */
public class PrintCostCalculator implements CostCalculator {
    private static final Logger logger = LoggerFactory.getLogger(PrintCostCalculator.class);
    private volatile static PrintCostCalculator uniqueInstance;

    /**
     * Constructor should be private for a singleton class
     */
    private PrintCostCalculator() {

    }

    /**
     * Create a unique instance of the PrintCostCalculator.
     *
     * @return a single unique instance.
     */
    public static CostCalculator getInstance() {
        if (uniqueInstance == null) {
            synchronized (PrintCostCalculator.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new PrintCostCalculator();
                }
            }
        }
        return uniqueInstance;
    }

    /**
     * Enum for storing the single sided page print costs.
     */
    private enum A4_SINGLE_SIDED_COST {
        BLACK_AND_WHITE_PAGE(BigDecimal.valueOf(0.15)), COLOUR_PAGE(BigDecimal.valueOf(0.25));
        private BigDecimal cost;

        A4_SINGLE_SIDED_COST(BigDecimal cost) {
            this.cost = cost;
        }

        public BigDecimal getCost() {
            return cost;
        }

    }

    /**
     * Enum for the double sided page print costs.
     */
    private enum A4_DOUBLE_SIDED_COST {
        BLACK_AND_WHITE_PAGE(BigDecimal.valueOf(0.10)), COLOUR_PAGE(BigDecimal.valueOf(0.20));
        private BigDecimal cost;

        A4_DOUBLE_SIDED_COST(BigDecimal cost) {
            this.cost = cost;
        }

        public BigDecimal getCost() {
            return cost;
        }

    }

    /**
     * Calculate the total cost of the printing
     *
     * @param schoolPrintJob- input the schoolPrintJob containing the required values for cost calculation
     * @return cost of printing the job.
     */
    public BigDecimal calculateCost(PrintJob schoolPrintJob) throws PrintCalculationException {
        if (schoolPrintJob == null) {
            logger.error("SchoolPrintJob can't be null");
            throw new PrintCalculationException("SchoolPrintJob can't be null");
        }
        int colorPages = schoolPrintJob.getNoOfColorPages();
        int blackAndWhitePages = schoolPrintJob.getTotalNumberOfPages() - colorPages;
        logger.debug(" Started calculating the cost of the schoolPrintJob {}", schoolPrintJob);
        if (schoolPrintJob.getPaperSize() != null && schoolPrintJob.getPaperSize().equals(Paper.SIZE.A4)) {
            if (schoolPrintJob.isDoubleSided()) {
                return BigDecimal.valueOf(blackAndWhitePages).multiply(A4_DOUBLE_SIDED_COST.BLACK_AND_WHITE_PAGE.getCost()).
                        add(BigDecimal.valueOf(colorPages).multiply(A4_DOUBLE_SIDED_COST.COLOUR_PAGE.getCost()));
            } else {
                return BigDecimal.valueOf(blackAndWhitePages).multiply(A4_SINGLE_SIDED_COST.BLACK_AND_WHITE_PAGE.getCost()).
                        add(BigDecimal.valueOf(colorPages).multiply(A4_SINGLE_SIDED_COST.COLOUR_PAGE.getCost()));
            }
        }
        logger.error("Can't find any cost rule to apply for paper size {} ", schoolPrintJob.getPaperSize());
        throw new PrintCalculationException("No valid paper size found to calculate cost");
    }
}
