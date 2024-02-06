package com.checkmate.exceptions.email;

/**
 * Exception thrown when an email address is null.
 *
 * @since 1.1.0-alpha
 */
public class NullEmailException extends RuntimeException {

    /**
     * Constructs a new {@code NullEmailException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public NullEmailException(String message) {
        super(message);
    }
}