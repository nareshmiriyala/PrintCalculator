package com.papercut.model;

import com.papercut.print.Paper;
import com.papercut.print.Paper.SIDE;
import com.papercut.print.Paper.SIZE;

import java.math.BigDecimal;

/**
 * Class to store the printing cost data.
 * Two PrintCostData objects are equal only when paperSize and paperSide values are equal.
 * Created by nareshm on 6/09/2015.
 */
public class PrintCostData {

    /**
     * Paper size.
     */
    private Paper.SIZE paperSize;
    /**
     * Sides of paper
     */
    private SIDE paperSide;
    /**
     * Black and White paper cost.
     */
    private BigDecimal blackAndWhitePaperCost;
    /**
     * Colour paper cost.
     */
    private BigDecimal colorPaperCost;

    public PrintCostData(Paper.SIZE paperSize, SIDE paperSide) {
        this.paperSize = paperSize;
        this.paperSide = paperSide;
    }

    public PrintCostData(SIZE paperSize, SIDE paperSide, BigDecimal blackAndWhitePaperCost, BigDecimal colorPaperCost) {
        this.paperSize = paperSize;
        this.paperSide = paperSide;
        this.blackAndWhitePaperCost = blackAndWhitePaperCost;
        this.colorPaperCost = colorPaperCost;
    }

    public Paper.SIZE getPaperSize() {
        return this.paperSize;
    }

    public void setPaperSize(Paper.SIZE paperSize) {
        this.paperSize = paperSize;
    }

    public SIDE getPaperSide() {
        return this.paperSide;
    }

    public void setPaperSide(SIDE paperSide) {
        this.paperSide = paperSide;
    }

    public BigDecimal getBlackAndWhitePaperCost() {
        return this.blackAndWhitePaperCost;
    }

    public void setBlackAndWhitePaperCost(BigDecimal blackAndWhitePaperCost) {
        this.blackAndWhitePaperCost = blackAndWhitePaperCost;
    }

    public BigDecimal getColorPaperCost() {
        return this.colorPaperCost;
    }

    public void setColorPaperCost(BigDecimal colorPaperCost) {
        this.colorPaperCost = colorPaperCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        PrintCostData printCostData = (PrintCostData) o;

        if (this.paperSize != printCostData.paperSize) return false;
        return this.paperSide == printCostData.paperSide;

    }

    @Override
    public int hashCode() {
        int result = this.paperSize.hashCode();
        result = 31 * result + this.paperSide.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PrintCostData{" +
                "paperSize=" + paperSize +
                ", paperSide=" + paperSide +
                ", blackAndWhitePaperCost=" + blackAndWhitePaperCost +
                ", colorPaperCost=" + colorPaperCost +
                '}';
    }
}
