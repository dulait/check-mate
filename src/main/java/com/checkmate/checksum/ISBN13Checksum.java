package com.checkmate.checksum;

/**
 * The ISBN13Checksum class provides a method for generating a check-digit of an ISBN-13 number.
 *
 * @since 1.1.0
 */
public class ISBN13Checksum extends Checksum {
    private static final ISBN13Checksum instance = new ISBN13Checksum();

    /**
     * Gets the singleton instance of ISBN13Checksum.
     *
     * @return The singleton instance of ISBN13Checksum.
     */
    public static ISBN13Checksum getInstance() {
        return instance;
    }

    /**
     * Private constructor for the ISBN13Checksum class
     */
    private ISBN13Checksum() {
    }

    @Override
    protected int getModulus() {
        return 10;
    }

    @Override
    protected int getRadix() {
        return 10;
    }

    @Override
    protected String getCharset() {
        return "0123456789";
    }

    @Override
    protected boolean hasTwoCheckDigits() {
        return false;
    }

    /**
     * Validates the given ISBN-13 number against the ISBN-13 checksum algorithm.
     *
     * @param isbn The ISBN-13 number to be validated.
     * @return {@code true} if the ISBN-13 number is valid according to the checksum algorithm, {@code false} otherwise.
     * @since 1.1.0
     */
    @Override
    public boolean isValid(String isbn) {
        int numOfDigits = hasTwoCheckDigits() ? 2 : 1;
        String stringWithoutChecksum = isbn.substring(0, isbn.length() - numOfDigits);

        return isbn.equals(calculate(stringWithoutChecksum));
    }

    /**
     * Calculates the checksum of the given ISBN-13 number.
     *
     * @param isbn The ISBN-13 number for which the checksum digit will be generated.
     * @return The valid ISBN-13 number with an appropriate checksum digit.
     * @since 1.1.0
     */
    public String calculate(String isbn) {
        isbn = isbn.replaceAll("[^0-9]", "");

        int sum = 0;
        int m = getModulus();
        int r = getRadix();
        for (int i = 0; i < isbn.length(); i++) {
            sum += Character.getNumericValue(isbn.charAt(i)) * ((i % 2 == 0) ? 1 : 3);
        }

        int remainder = sum % m;
        int checkDigit = (remainder == 0) ? 0 : m - remainder;

        return isbn + ((checkDigit == r) ? '0' : (char) ('0' + checkDigit));
    }

}