package com.checkmate.exceptions.iban;

/**
 * Exception thrown when an IBAN is empty.
 *
 * @since 1.1.0-alpha
 */
public class EmptyIBANException extends RuntimeException {

    /**
     * Constructs a new {@code EmptyIBANException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmptyIBANException(String message) {
        super(message);
    }
}