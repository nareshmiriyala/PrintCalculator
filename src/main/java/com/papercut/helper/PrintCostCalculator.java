package com.papercut.helper;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.SchoolPrintJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

/**
 * Helper class to calculate the total cost of printing a job.
 * Its a Singleton class.
 * Created by nareshm on 4/09/2015.
 */
public class PrintCostCalculator {
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
    public static PrintCostCalculator getInstance() {
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
    private enum SINGLE_SIDED_COST {
        BLACK_AND_WHITE_PAGE(BigDecimal.valueOf(0.15)), COLOUR_PAGE(BigDecimal.valueOf(0.25));
        private BigDecimal cost;

        SINGLE_SIDED_COST(BigDecimal cost) {
            this.cost = cost;
        }

        public BigDecimal getCost() {
            return cost;
        }

    }

    /**
     * Enum for the double sided page print costs.
     */
    private enum DOUBLE_SIDED_COST {
        BLACK_AND_WHITE_PAGE(BigDecimal.valueOf(0.10)), COLOUR_PAGE(BigDecimal.valueOf(0.20));
        private BigDecimal cost;

        DOUBLE_SIDED_COST(BigDecimal cost) {
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
    public static BigDecimal calculateCost(SchoolPrintJob schoolPrintJob) throws PrintCalculationException {
        if (schoolPrintJob == null) {
            logger.error("SchoolPrintJob can't be null");
            throw new PrintCalculationException("SchoolPrintJob can't be null");
        }
        int colorPages = schoolPrintJob.getNoOfColorPages();
        int blackAndWhitePages = schoolPrintJob.getTotalNumberOfPages() - colorPages;
        logger.debug(" Started calculating the cost of the schoolPrintJob {}", schoolPrintJob);
        if (schoolPrintJob.isDoubleSided()) {
            return BigDecimal.valueOf(blackAndWhitePages).multiply(DOUBLE_SIDED_COST.BLACK_AND_WHITE_PAGE.getCost()).
                    add(BigDecimal.valueOf(colorPages).multiply(DOUBLE_SIDED_COST.COLOUR_PAGE.getCost()));
        } else {
            return BigDecimal.valueOf(blackAndWhitePages).multiply(SINGLE_SIDED_COST.BLACK_AND_WHITE_PAGE.getCost()).
                    add(BigDecimal.valueOf(colorPages).multiply(SINGLE_SIDED_COST.COLOUR_PAGE.getCost()));
        }

    }
}
