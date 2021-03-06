package com.papercut.exceptions;

/**
 * The {@code InvalidInputException} class used to indicate that an exceptional
 * condition has occurred in the Values Input to print system. Created by
 * nareshm on 4/09/2015.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new {@code InvalidInputException} object without a detail
     * message.
     */
    public InvalidInputException() {

    }

    /**
     * Constructs a new {@code InvalidInputException} object with the specified
     * detail message.
     *
     * @param msg the message to generate when a {@code InvalidInputException}
     * is thrown
     */
    public InvalidInputException(String msg) {
        super(msg);
    }
}
