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
     * @return
     */
    long getJobId();

    /**
     * Get the job name of the print job.
     * @return
     */
    String getJobName();

    /**
     * Get the paper size of the print job.
     * @return
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
     * @return
     */
    boolean isDoubleSided();

    /**
     * get the total number of pages to be printed by this job.
     * @return
     */
    int getTotalNumberOfPages();

    /**
     * Get the total of color pages printed by this job.
     * @return
     */
    int getNoOfColorPages();
}
