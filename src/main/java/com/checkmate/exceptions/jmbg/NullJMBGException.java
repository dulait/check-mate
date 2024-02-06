package com.checkmate.exceptions.jmbg;

/**
 * Exception thrown when a JMBG is null.
 *
 * @since 1.1.0-alpha
 */
public class NullJMBGException extends RuntimeException {

    /**
     * Constructs a new {@code NullJMBGException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public NullJMBGException(String message) {
        super(message);
    }
}