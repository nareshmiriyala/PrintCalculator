package com.papercut.print;


import com.papercut.exceptions.InvalidInputException;
import com.papercut.exceptions.PrintCalculationException;
import com.papercut.helper.PrintCostCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * An class which initiates and executes a school print job.
 * Created by nareshm on 4/09/2015.
 */
public class SchoolPrintJob implements PrintJob {

    private static final Logger logger = LoggerFactory.getLogger(SchoolPrintJob.class);
    /**
     * Unique id for this print job.
     */
    private long jobId;
    /**
     * print job name
     */
    private String jobName;
    /**
     * Paper size of the document to be printed by this job.
     */
    private Paper.SIZE paperSize;

    private boolean doubleSided;

    private int totalNumberOfPages;

    private int noOfColorPages;

    /**
     * Get the id of the document to be printed.
     *
     * @return the id of the document to be printed.
     */
    public long getJobId() {
        return jobId;
    }


    /**
     * Gets the name of the document to be printed.
     *
     * @return the name of the document to be printed.
     */
    public String getJobName() {
        return jobName;
    }


    /**
     * Gets the paper size of the document to be printed.
     *
     * @return the paper size  of the document to be printed.
     */
    public Paper.SIZE getPaperSize() {
        return paperSize;
    }

    /**
     * Print the document
     */
    public void print() {
    }

    /**
     * Cancel the printing of the document
     */
    public void cancel() {

    }

    /**
     * Calculates the cost of the document to be printed.
     *
     * @return cost of the printing this job
     */
    public BigDecimal cost() throws PrintCalculationException {
        return PrintCostCalculator.getInstance().calculateCost(this);
    }

    /**
     * Check if the printed job is doubleSided
     *
     * @return true if the printed job is double sided ,else returns false
     */
    public boolean isDoubleSided() {
        return doubleSided;
    }


    /**
     * Get the total number of pages printed by this print job
     *
     * @return gets the total no of pages in the document printed
     */
    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }


    /**
     * Gets the total number of color pages printed in this job
     *
     * @return no of color pages
     */
    public int getNoOfColorPages() {
        return noOfColorPages;
    }


    private SchoolPrintJob(SchoolPrintJobBuilder schoolPrintJobBuilder) {
        this.jobId = schoolPrintJobBuilder.jobId;
        this.jobName = schoolPrintJobBuilder.jobName;
        this.paperSize = schoolPrintJobBuilder.paperSize;
        this.doubleSided = schoolPrintJobBuilder.doubleSided;
        this.totalNumberOfPages = schoolPrintJobBuilder.totalNumberOfPages;
        this.noOfColorPages = schoolPrintJobBuilder.noOfColorPages;
    }

    /**
     * School Print job builder.
     */
    public static class SchoolPrintJobBuilder {

        private long jobId;

        private String jobName;

        private Paper.SIZE paperSize;

        private boolean doubleSided;

        private int totalNumberOfPages;

        private int noOfColorPages;

        /**
         * Set the id of the document to be printed.
         *
         * @param jobId the id of the document to be printed,mandatory for the builder.
         */
        public SchoolPrintJobBuilder(long jobId) throws InvalidInputException {
            validateInputNumber(jobId);
            this.jobId = jobId;
        }

        /**
         * Sets the name of the document to be printed.
         *
         * @param jobName the name of the document to be printed
         */
        public SchoolPrintJobBuilder withJobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        /**
         * Sets the paper size of the document to be printed.
         * The document paperSize cannot be null
         *
         * @param paperSize the paperSize of the document to be printed
         */
        public SchoolPrintJobBuilder withPaperSize(Paper.SIZE paperSize) {
            this.paperSize = paperSize;
            return this;
        }

        /**
         * Set the printed job as double sided print
         *
         * @param doubleSided - sets true
         */
        public SchoolPrintJobBuilder isDoubleSidedPrint(boolean doubleSided) {
            this.doubleSided = doubleSided;
            return this;
        }

        /**
         * Sets the total number of pages printed
         *
         * @param totalNumberOfPages
         */
        public SchoolPrintJobBuilder withTotalPrintPages(int totalNumberOfPages) throws InvalidInputException {
            validateInputNumber(totalNumberOfPages);
            this.totalNumberOfPages = totalNumberOfPages;
            return this;
        }

        /**
         * Set the total of number of color pages printed.
         *
         * @param noOfColorPages - no of color pages,this value should be less than equal to totalNumberOfPages
         */
        public SchoolPrintJobBuilder withNoOfColorPages(int noOfColorPages) throws InvalidInputException {
            if (noOfColorPages > totalNumberOfPages) {
                logger.error("Number of color pages {} is greater than the total number of pages {}", noOfColorPages, totalNumberOfPages);
                throw new InvalidInputException("Number of color pages can't be greater than the total number of pages");
            }
            this.noOfColorPages = noOfColorPages;
            return this;
        }

        public SchoolPrintJob build() {
            return new SchoolPrintJob(this);
        }

    }

    private static void validateInputNumber(long jobId) throws InvalidInputException {
        if (jobId <= 0) {
            logger.error("Value can't be {}", jobId);
            throw new InvalidInputException("Input is invalid.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolPrintJob schoolPrintJob = (SchoolPrintJob) o;

        return jobId == schoolPrintJob.jobId;

    }

    @Override
    public int hashCode() {
        return (int) (jobId ^ (jobId >>> 32));
    }

    @Override
    public String toString() {
        return "SchoolPrintJob{" +
                "jobId=" + jobId +
                ", jobName='" + jobName + '\'' +
                ", paperSize=" + paperSize +
                ", doubleSided=" + doubleSided +
                ", totalNumberOfPages=" + totalNumberOfPages +
                ", noOfColorPages=" + noOfColorPages +
                '}';
    }
}
