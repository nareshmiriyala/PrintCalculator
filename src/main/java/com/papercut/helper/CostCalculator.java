package com.papercut.helper;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.model.PrintCostData;
import com.papercut.print.PrintJob;

import java.math.BigDecimal;

/**
 * Interface for calculating the cost of printJob Created by nareshm on
 * 5/09/2015.
 */
public interface CostCalculator {

    void insertPrintCostData(PrintCostData printCostData);

    BigDecimal calculateCost(PrintJob schoolPrintJob) throws PrintCalculationException;
}
