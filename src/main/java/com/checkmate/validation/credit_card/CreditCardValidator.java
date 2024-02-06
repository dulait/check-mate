package com.checkmate.validation.credit_card;

import com.checkmate.checksum.CreditCardChecksum;
import com.checkmate.exceptions.credit_card.EmptyCreditCardException;
import com.checkmate.exceptions.credit_card.NullCreditCardException;
import com.checkmate.validation.Validator;

/**
 * The CreditCardValidator class provides credit card number validations
 *
 * @since 1.1.0-alpha
 */
public class CreditCardValidator implements Validator<String> {

    private static final CreditCardValidator instance = new CreditCardValidator();
    private static final int MIN_CARD_NUMBER_LENGTH = 8;
    private static final int MAX_CARD_NUMBER_LENGTH = 19;

    /**
     * Default constructor for the CreditCardValidator class
     */
    public CreditCardValidator() {
    }

    /**
     * Gets the singleton instance of the CreditCardValidator.
     *
     * @return The singleton instance of the CreditCardValidator.
     */
    public static CreditCardValidator getInstance() {
        return instance;
    }

    /**
     * Checks if the credit card number is valid.
     *
     * @param number The credit card number to validate.
     * @return {@code true} if the credit card number is valid, {@code false} otherwise.
     * @throws NullCreditCardException  if the credit card number is null or an empty string.
     * @throws EmptyCreditCardException if the credit card number is an empty string.
     */
    @Override
    public boolean isValid(String number) {

        if (number.length() < MIN_CARD_NUMBER_LENGTH || number.length() > MAX_CARD_NUMBER_LENGTH) {
            return false;
        }

        String cleanNumber = number.replaceAll("[^0-9]", "");
        return CreditCardChecksum.getInstance().isValid(cleanNumber);
    }

    /**
     * Checks if the credit card number is null or an empty string.
     *
     * @param number The credit card number to check.
     * @return {@code true} if the credit card number is null or an empty string, {@code false} otherwise.
     * @throws NullCreditCardException  if the credit card number is null or an empty string.
     * @throws EmptyCreditCardException if the credit card number is an empty string.
     */
    @Override
    public boolean isNullOrEmpty(String number) {
        if (number == null) {
            throw new NullCreditCardException("The credit card number cannot be null");
        }
        if (number.trim().isEmpty()) {
            throw new EmptyCreditCardException("The credit card cannot be an empty string");
        }
        return false;
    }

}