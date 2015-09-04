package com.papercut.print;

/**
 * The Paper class describes the physical characteristics of
 * a piece of paper.
 */
public class Paper {
    private SIZE paperSize;

    public Paper(SIZE paperSize) {
        this.paperSize = paperSize;
    }

    public SIZE getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(SIZE paperSize) {
        this.paperSize = paperSize;
    }

    /**
     * Enum to represent the size of paper
     */
    public enum SIZE {
        A4, LETTER, A3
    }

}
