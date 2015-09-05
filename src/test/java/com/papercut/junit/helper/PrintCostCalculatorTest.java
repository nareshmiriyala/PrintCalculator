package com.papercut.junit.helper;

import com.papercut.exceptions.PrintCalculationException;
import com.papercut.helper.PrintCostCalculator;
import com.papercut.print.SchoolPrintJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * Created by nareshm on 5/09/2015.
 */
@RunWith(PowerMockRunner.class)
public class PrintCostCalculatorTest {

    @Mock
    private SchoolPrintJob mockSchoolPrintJob;

    /**
     * Test if instances are same.
     *
     * @throws Exception
     */
    @Test
    public void testGetInstance() throws Exception {
        assertTrue(PrintCostCalculator.getInstance().equals(PrintCostCalculator.getInstance()));
    }

    @Test(expected = PrintCalculationException.class)
    public void testCalculateCostInvalidInput() throws Exception {
        PrintCostCalculator.getInstance().calculateCost(null);

    }

    @Test
    public void testCalculateCost() throws Exception {

        PrintCostCalculator.getInstance().calculateCost(mockSchoolPrintJob);
        Mockito.verify(mockSchoolPrintJob, times(1)).getTotalNumberOfPages();
        Mockito.verify(mockSchoolPrintJob, times(1)).getNoOfColorPages();
        Mockito.verify(mockSchoolPrintJob, times(1)).isDoubleSided();

    }
}