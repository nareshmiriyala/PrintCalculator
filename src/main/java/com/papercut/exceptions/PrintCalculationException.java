package com.papercut.exceptions;

/**
 * The <code>PrintCalculationException</code> class used
 * to indicate that an exceptional condition has occurred in the print
 * system.
 * Created by nareshm on 4/09/2015.
 */
public class PrintCalculationException extends Exception {
    /**
     * Constructs a new <code>PrintCalculationException</code> object
     * without a detail message.
     */
    public PrintCalculationException() {

    }

    /**
     * Constructs a new <code>PrintCalculationException</code> object
     * with the specified detail message.
     *
     * @param msg the message to generate when a
     *            <code>PrintCalculationException</code> is thrown
     */
    public PrintCalculationException(String msg) {
        super(msg);
    }
}
