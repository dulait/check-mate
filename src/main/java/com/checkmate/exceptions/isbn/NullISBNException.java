package com.checkmate.exceptions.isbn;

/**
 * Exception thrown when an ISBN is null.
 *
 * @since 1.1.0-alpha
 */
public class NullISBNException extends RuntimeException {

    /**
     * Constructs a new {@code NullISBNException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public NullISBNException(String message) {
        super(message);
    }
}