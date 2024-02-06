package com.checkmate.exceptions.iban;

/**
 * Exception thrown when an IBAN is null.
 *
 * @since 1.1.0-alpha
 */
public class NullIBANException extends RuntimeException {

    /**
     * Constructs a new {@code NullIBANException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public NullIBANException(String message) {
        super(message);
    }
}