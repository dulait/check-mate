package com.checkmate.exceptions.jmbg;

/**
 * Exception thrown when a JMBG is empty.
 *
 * @since 1.1.0-alpha
 */
public class EmptyJMBGException extends RuntimeException {
    /**
     * Constructs a new {@code EmptyJMBGException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmptyJMBGException(String message) {
        super(message);
    }
}