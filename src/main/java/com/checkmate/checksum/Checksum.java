package com.checkmate.checksum;

/**
 * <p>
 * The Checksum class serves as a base class for implementing various checksum algorithms.
 * Checksum algorithms are used for error-checking purposes in codes.
 * </p>
 *
 * @since 1.1.0-alpha
 */
public abstract class Checksum {

    /**
     * Private constructor for the Checksum class
     */
    Checksum() {
    }

    /**
     * Gets the modulus value used in the checksum algorithm.
     *
     * @return The modulus value.
     */
    protected abstract int getModulus();

    /**
     * Gets the radix value used in the checksum algorithm.
     *
     * @return The radix value.
     */
    protected abstract int getRadix();

    /**
     * Gets the character set used in the checksum algorithm.
     *
     * @return The character set.
     */
    protected abstract String getCharset();

    /**
     * Indicates whether the checksum algorithm requires two check digits.
     *
     * @return {@code true} if two check digits are required, {@code false} otherwise.
     */
    protected abstract boolean hasTwoCheckDigits();

    /**
     * Validates the input against the checksum algorithm.
     *
     * @param input The input to be validated.
     * @return {@code true} if the input is valid according to the checksum algorithm, {@code false} otherwise.
     */
    protected abstract boolean isValid(String input);

}