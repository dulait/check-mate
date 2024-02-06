package com.checkmate.exceptions.credit_card;

/**
 * Exception thrown when a credit card number is an empty string.
 *
 * @since 1.1.0-alpha
 */

public class EmptyCreditCardException extends RuntimeException {

    /**
     * Constructs a new {@code EmptyCreditCardException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmptyCreditCardException(String message) {
        super(message);
    }
}