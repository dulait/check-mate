package com.checkmate.exceptions.credit_card;

/**
 * Exception thrown when a credit card number is null.
 *
 * @since 1.1.0-alpha
 */
public class NullCreditCardException extends RuntimeException {

    /**
     * Constructs a new {@code NullCreditCardException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public NullCreditCardException(String message) {
        super(message);
    }
}