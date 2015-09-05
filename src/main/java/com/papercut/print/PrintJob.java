package com.papercut.print;

import com.papercut.exceptions.PrintCalculationException;

import java.math.BigDecimal;

/**
 * Interface to represent the operations of the Print Job
 * Created by nareshm on 4/09/2015.
 */
public interface PrintJob {
    /**
     * Get the job id
     *
     * @return jobId
     */
    long getJobId();

    /**
     * Get the job name of the print job.
     *
     * @return jobName
     */
    String getJobName();

    /**
     * Get the paper size of the print job.
     *
     * @return size of papre
     */
    Paper.SIZE getPaperSize();

    /**
     * Print the document
     */
    void print();

    /**
     * Cancel the document being printed.
     */
    void cancel();

    /**
     * Get the total cost of the print job.
     *
     * @return
     */
    BigDecimal cost() throws PrintCalculationException;

    /**
     * check if the document to printed is double sided.
     *
     * @return if the print job is double sided
     */
    boolean isDoubleSided();

    /**
     * get the total number of pages to be printed by this job.
     *
     * @return total number of pages being printed in this job
     */
    int getTotalNumberOfPages();

    /**
     * Get the total of color pages printed by this job.
     *
     * @return total number of color pages printed in this job
     */
    int getNoOfColorPages();
}
