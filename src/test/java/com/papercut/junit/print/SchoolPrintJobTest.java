package com.papercut.junit.print;

import com.papercut.exceptions.InvalidInputException;
import com.papercut.print.Paper;
import com.papercut.print.SchoolPrintJob;
import com.papercut.util.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit Test for SchoolPrintJobTest.
 * Created by nareshm on 5/09/2015.
 */
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
        schoolPrintJob = new SchoolPrintJob.SchoolPrintJobBuilder(1).withPaperSize(Paper.SIZE.A4).withTotalPrintPages(10).build();
        BigDecimal cost = schoolPrintJob.cost();
        assertCost(1.50, cost);
    }

    private void assertCost(double actual, BigDecimal cost) {
        assertEquals("Cost value is invalid", Utility.getRoundedValue(BigDecimal.valueOf(actual)), Utility.getRoundedValue(cost));

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

    /**
     * Test when totalNoOfPages,noOfColorPages,doubleSided values are not provided
     *
     * @throws Exception
     */
    @Test
    public void testCostWhenAllThreeValuesNotProvided() throws Exception {
        schoolPrintJob = new SchoolPrintJob.SchoolPrintJobBuilder(1).withPaperSize(Paper.SIZE.A4).build();
        assertCost(0, schoolPrintJob.cost());
    }

    @Test
    public void testCostWhenAllValuesProvided() throws Exception {
        BigDecimal cost = new SchoolPrintJob.SchoolPrintJobBuilder(1).withPaperSize(Paper.SIZE.A4).withTotalPrintPages(25).withNoOfColorPages(10).isDoubleSidedPrint(false).build().cost();
        assertCost(4.75, cost);
    }

    /**
     * Two SchoolPrintJob instances are equal only when their jobids are equal
     *
     * @throws Exception
     */
    @Test
    public void testEqualsSuccess() throws Exception {
        assertTrue(new SchoolPrintJob.SchoolPrintJobBuilder(451).build().equals(new SchoolPrintJob.SchoolPrintJobBuilder(451).build()));
    }

    /**
     * Two SchoolPrintJob instance jobids are not equal.
     *
     * @throws Exception
     */
    @Test
    public void testEqualsFailure() throws Exception {
        assertFalse(new SchoolPrintJob.SchoolPrintJobBuilder(555).build().equals(new SchoolPrintJob.SchoolPrintJobBuilder(451).build()));
    }


}