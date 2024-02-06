package com.checkmate.validation;

import java.io.Serializable;

/**
 * A generic interface for validating objects.
 * Implementations should provide specific validation logic for the given type.
 *
 * @param <T> The type of object to be validated.
 * @since 1.1.0-alpha
 */
public interface Validator<T> extends Serializable {

    /**
     * Validates the given object.
     *
     * @param input The object to be validated.
     * @return {@code true} if the object is considered valid, {@code false} otherwise.
     */
    boolean isValid(T input);

    /**
     * Checks if the given object is null or an empty string (in case of a String object).
     *
     * @param input The object to be validated.
     * @return {@code true} if the object is null or an empty string (in case of a String object), {@code false} otherwise.
     */
    boolean isNullOrEmpty(T input);

}