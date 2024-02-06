package com.checkmate.checksum;

/**
 * The ISBN10Checksum class provides a method for generating a check-digit of an ISBN-10 number.
 *
 * @since 1.1.0-alpha
 */
public class ISBN10Checksum extends Checksum {
    private static final ISBN10Checksum instance = new ISBN10Checksum();

    /**
     * Gets the singleton instance of ISBN10Checksum.
     *
     * @return The singleton instance of ISBN10Checksum.
     */
    public static ISBN10Checksum getInstance() {
        return instance;
    }

    /**
     * Default constructor for the ISBN10Checksum class
     */
    public ISBN10Checksum() {
    }

    @Override
    protected int getModulus() {
        return 11;
    }

    @Override
    protected int getRadix() {
        return 10;
    }

    @Override
    protected String getCharset() {
        return "0123456789X";
    }

    @Override
    protected boolean hasTwoCheckDigits() {
        return false;
    }

    /**
     * Validates the given ISBN-10 number against the ISBN-10 checksum algorithm.
     *
     * @param isbn The ISBN-10 number to be validated.
     * @return {@code true} if the ISBN-10 number is valid according to the checksum algorithm, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String isbn) {
        int numOfDigits = hasTwoCheckDigits() ? 2 : 1;
        String stringWithoutChecksum = isbn.substring(0, isbn.length() - numOfDigits);

        return isbn.equals(calculate(stringWithoutChecksum));
    }

    /**
     * Calculates the checksum of the given ISBN-10 number.
     *
     * @param isbn The ISBN for which the check-digit will be generated.
     * @return The valid ISBN-10 number with an appropriate checksum digit.
     */
    public String calculate(String isbn) {

        isbn = isbn.replaceAll("[^0-9]", "");

        int sum = 0;
        int m = getModulus();
        int r = getRadix();
        for (int i = 0; i < isbn.length(); i++) {
            sum += Character.getNumericValue(isbn.charAt(i)) * (r - i);
        }

        int remainder = sum % m;
        int checkDigit = (remainder == 0) ? 0 : m - remainder;

        return isbn + ((checkDigit == r) ? 'X' : (char) ('0' + checkDigit));
    }


}