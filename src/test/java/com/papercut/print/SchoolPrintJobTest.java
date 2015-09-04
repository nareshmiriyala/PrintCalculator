package com.papercut.print;

import com.papercut.exceptions.InvalidInputException;
import com.papercut.util.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit Test for SchoolPrintJobTest.
 * Created by nareshm on 5/09/2015.
 */
@RunWith(PowerMockRunner.class)
public class SchoolPrintJobTest {

    private SchoolPrintJob schoolPrintJob;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test when total noOfColorPages and doubleSided value not provided.
     * Default value of noOfColorPages is 0 and doubleSided is false,hence cost will be 10*15=1.5
     * All the pages are black and white.
     *
     * @throws Exception
     */
    @Test
    public void testCostWhenNoOfColorsAndDoubleSidedValuesNotProvided() throws Exception {
        schoolPrintJob = new SchoolPrintJob.SchoolPrintJobBuilder(1).withTotalPrintPages(10).build();
        BigDecimal cost = schoolPrintJob.cost();
        assertCost(1.50, cost);
    }

    private void assertCost(double actual, BigDecimal cost) {
        assertEquals("Value should be 1.50", Utility.getRoundedValue(BigDecimal.valueOf(actual)), Utility.getRoundedValue(cost));

    }

    /**
     * Test should throw exception as the noOfColorPages is greater than the totalNoOfPages
     *
     * @throws Exception
     */
    @Test(expected = InvalidInputException.class)
    public void testCostWhenTotalNoOfPagesNotProvided() throws Exception {
        schoolPrintJob = new SchoolPrintJob.SchoolPrintJobBuilder(1).withNoOfColorPages(5).isDoubleSidedPrint(true).build();
        schoolPrintJob.cost();
    }

    @Test
    public void testCostWhenAllThreeValuesNotProvided() throws Exception {
        schoolPrintJob = new SchoolPrintJob.SchoolPrintJobBuilder(1).build();
        assertCost(0, schoolPrintJob.cost());
    }

}