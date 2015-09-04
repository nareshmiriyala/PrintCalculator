package com.papercut.helper;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.print.SchoolPrintJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Helper class to calculate the total cost of printing for a job.
 * Its a Singleton object.
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

    private enum SINGLE_SIDED_COST {
        BLACK_AND_WHITE_PAGE(new BigDecimal(0.15)), COLOUR_PAGE(new BigDecimal(0.25));
        private BigDecimal cost;

        SINGLE_SIDED_COST(BigDecimal cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "DOUBLE_SIDED_COST{" +
                    "cost=" + cost +
                    '}';
        }

        public BigDecimal getCost() {
            return cost;
        }

        public void setCost(BigDecimal cost) {
            this.cost = cost;
        }
    }

    private enum DOUBLE_SIDED_COST {
        BLACK_AND_WHITE_PAGE(new BigDecimal(0.10)), COLOUR_PAGE(new BigDecimal(0.20));
        private BigDecimal cost;

        DOUBLE_SIDED_COST(BigDecimal cost) {
            this.cost = cost;
        }

        public BigDecimal getCost() {
            return cost;
        }

        public void setCost(BigDecimal cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "DOUBLE_SIDED_COST{" +
                    "cost=" + cost +
                    '}';
        }
    }

    /**
     * Calculate the total cost of the printing
     *
     * @param schoolPrintJob
     * @return
     */
    public static BigDecimal calculateCost(SchoolPrintJob schoolPrintJob) throws PrintCalculationException {
        if(schoolPrintJob==null){
            logger.error("SchoolPrintJob can't be null");
            throw new PrintCalculationException("SchoolPrintJob can't be null");
        }
        int totalNoOfBlackAndWhitePages = schoolPrintJob.getTotalNumberOfPages() - schoolPrintJob.getNoOfColorPages();
        logger.debug(" Started calculating the cost of the schoolPrintJob {}",schoolPrintJob);
        if (schoolPrintJob.isDoubleSided()) {
            return BigDecimal.valueOf(totalNoOfBlackAndWhitePages).multiply(DOUBLE_SIDED_COST.BLACK_AND_WHITE_PAGE.getCost()).add(BigDecimal.valueOf(schoolPrintJob.getNoOfColorPages()).multiply(DOUBLE_SIDED_COST.COLOUR_PAGE.getCost()));
        } else {
            return BigDecimal.valueOf(totalNoOfBlackAndWhitePages).multiply(SINGLE_SIDED_COST.BLACK_AND_WHITE_PAGE.getCost()).add(BigDecimal.valueOf(schoolPrintJob.getNoOfColorPages()).multiply(SINGLE_SIDED_COST.COLOUR_PAGE.getCost()));
        }

    }
}
