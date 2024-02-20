package com.checkmate.checksum;

/**
 * The CreditCardValidator class provides methods for validating the checksum of credit card.
 *
 * @since 1.1.0
 */
public class CreditCardChecksum extends Checksum {

    private static final CreditCardChecksum instance = new CreditCardChecksum();

    /**
     * Private constructor for the CreditCardChecksum class
     */
    private CreditCardChecksum() {
    }

    /**
     * Gets the singleton instance of CreditCardChecksum.
     *
     * @return The singleton instance of CreditCardChecksum.
     */
    public static CreditCardChecksum getInstance() {
        return instance;
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
        return "0123456789"; // Not used in this class but definitely could be used, somewhere
    }

    @Override
    protected boolean hasTwoCheckDigits() {
        return false;
    }

    /**
     * Validates the given credit card number using the <a href="https://en.wikipedia.org/wiki/Luhn_algorithm">Luhn algorithm</a>.
     *
     * @param number The card number to be validated.
     * @return {@code true} if the card number is valid according to the checksum algorithm, {@code false} otherwise.
     * @since 1.1.0
     */
    @Override
    public boolean isValid(String number) {
        int[] numberArray = stringToIntArray(number);

        int checksum = numberArray[numberArray.length - 1];

        for (int j = numberArray.length - 2; j >= 0; j -= 2) {
            numberArray[j] *= 2;
            if (numberArray[j] > 9) {
                numberArray[j] -= 9;
            }
        }

        int totalSum = 0;
        for (int n = 0; n < numberArray.length - 1; n++) {
            totalSum += numberArray[n];
        }

        return ((totalSum * 9) % 10) == checksum;
    }

    // Helper method for translating the number to an int array
    private int[] stringToIntArray(String str) {
        int[] numberArray = new int[str.length()];
        for (int i = str.length() - 1; i >= 0; i--) {
            numberArray[i] = Integer.parseInt(str.substring(i, i + 1));
        }

        return numberArray;
    }

}