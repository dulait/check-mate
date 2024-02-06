package com.checkmate.exceptions.email;

/**
 * Exception thrown when an email address is empty.
 *
 * @since 1.1.0-alpha
 */
public class EmptyEmailException extends RuntimeException {

    /**
     * Constructs a new {@code EmptyEmailException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmptyEmailException(String message) {
        super(message);
    }
}