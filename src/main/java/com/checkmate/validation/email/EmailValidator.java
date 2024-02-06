package com.checkmate.validation.email;


import com.checkmate.exceptions.email.EmptyEmailException;
import com.checkmate.exceptions.email.NullEmailException;
import com.checkmate.validation.Validator;

import java.util.regex.Pattern;

/**
 * The EmailValidator class provides email validations
 *
 * @since 1.1.0-alpha
 */
public class EmailValidator implements Validator<String> {
    private static final EmailValidator instance = new EmailValidator();
    private static final String EMAIL_REGEX = "^(.+)@([^.]+)\\.(\\S+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Gets the singleton instance of the EmailValidator.
     *
     * @return The singleton instance of the EmailValidator.
     */
    public static EmailValidator getInstance() {
        return instance;
    }

    /**
     * Default constructor for the EmailValidator class
     */
    public EmailValidator() {

    }

    /**
     * Checks if an email address is valid.
     *
     * @param email The email address to validate.
     * @return {@code true} if the email address is valid, {@code false} otherwise.
     * @throws NullEmailException  if the email is null or an empty string.
     * @throws EmptyEmailException if the email is an empty string.
     */
    @Override
    public boolean isValid(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Checks if an email is null or an empty string.
     *
     * @param email The email address to check.
     * @return {@code true} if the email is null or an empty string, {@code false} otherwise.
     * @throws NullEmailException  if the email is null or an empty string.
     * @throws EmptyEmailException if the email is an empty string.
     */
    @Override
    public boolean isNullOrEmpty(String email) {
        if (email == null) {
            throw new NullEmailException("Email cannot be null");
        }
        if (email.trim().isEmpty()) {
            throw new EmptyEmailException("Email cannot be an empty string");
        }
        return false;
    }

}