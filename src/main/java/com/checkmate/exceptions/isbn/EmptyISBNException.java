package com.checkmate.exceptions.isbn;

/**
 * Exception thrown when an ISBN is empty.
 *
 * @since 1.1.0-alpha
 */
public class EmptyISBNException extends RuntimeException {
    /**
     * Constructs a new {@code EmptyISBNException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmptyISBNException(String message) {
        super(message);
    }
}