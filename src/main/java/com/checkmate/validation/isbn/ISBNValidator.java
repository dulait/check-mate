package com.checkmate.validation.isbn;

import com.checkmate.checksum.ISBN10Checksum;
import com.checkmate.checksum.ISBN13Checksum;
import com.checkmate.exceptions.isbn.EmptyISBNException;
import com.checkmate.exceptions.isbn.NullISBNException;
import com.checkmate.validation.Validator;

import java.util.regex.Pattern;

/**
 * The ISBNValidator class provides validations for ISBN-10 and ISBN-13 numbers.
 *
 * <p>
 * For more information on how ISBN numbers are validated visit
 * <a href="https://en.wikipedia.org/wiki/ISBN#Check_digits">the official Wikipedia page</a>
 * </p>
 *
 * @since 1.1.0
 */
public class ISBNValidator implements Validator<String> {

    private static final ISBNValidator instance = new ISBNValidator();

    /**
     * Private constructor for the ISBNValidator class
     */
    private ISBNValidator() {
    }

    private static final String ISBN10_REGEX = "^(?:ISBN(?:-10)?:?|-)?(?=[0-9X]{10}$|(?=(?:[0-9]+[-]){3})[-0-9X]{13}$)[0-9]{1,5}[-]?[0-9]+[-]?[0-9]+[-]?[0-9X]$";
    private static final String ISBN13_REGEX = "^(?:ISBN(?:-13)?:?|-)?(?=[0-9]{13}$|(?=(?:[0-9]+[-]){4})[-0-9]{17}$)97[89][-]?[0-9]{1,5}[-]?[0-9]+[-]?[0-9]+[-]?[0-9]$";
    private static final Pattern ISBN10_PATTERN = Pattern.compile(ISBN10_REGEX);
    private static final Pattern ISBN13_PATTERN = Pattern.compile(ISBN13_REGEX);

    /**
     * Gets the singleton instance of the ISBNValidator.
     *
     * @return The singleton instance of the ISBNValidator.
     */
    public static ISBNValidator getInstance() {
        return instance;
    }

    /**
     * Checks if the given ISBN matches either the ISBN-10 or ISBN-13 standard. <br>
     *
     * @param isbn The ISBN to validate.
     * @return {@code true} if the ISBN is valid, {@code false} otherwise.
     * @throws NullISBNException  if the input ISBN is null.
     * @throws EmptyISBNException if the input ISBN is an empty string.
     * @since 1.1.0
     */
    @Override
    public boolean isValid(String isbn) {
        return isValidISBN10(isbn) || isValidISBN13(isbn);
    }

    /**
     * Checks if the given ISBN matches the ISBN-10 standard. <br>
     *
     * @param isbn The ISBN to validate.
     * @return {@code true} if the ISBN is valid, {@code false} otherwise.
     * @throws NullISBNException  if the input ISBN is null.
     * @throws EmptyISBNException if the input ISBN is an empty string.
     * @since 1.1.0
     */
    public boolean isValidISBN10(String isbn) {
        if (validateISBN10(isbn) == null) {
            return false;
        }

        isbn = generateCleanISBN(isbn);

        return isbn.equals(ISBN10Checksum.getInstance().calculate(isbn.substring(0, 9)));
    }

    /**
     * Checks if the given ISBN matches the ISBN-13 standard. <br>
     *
     * @param isbn The ISBN to validate.
     * @return {@code true} if the ISBN is valid, {@code false} otherwise.
     * @throws NullISBNException  if the input ISBN is null.
     * @throws EmptyISBNException if the input ISBN is an empty string.
     * @since 1.1.0
     */
    public boolean isValidISBN13(String isbn) {
        if (validateISBN13(isbn) == null) {
            return false;
        }

        isbn = generateCleanISBN(isbn);

        return isbn.equals(ISBN13Checksum.getInstance().calculate(isbn.substring(0, 12)));
    }

    /**
     * Returns a valid ISBN-10 number, assuming every digit before checksum (last digit) is valid.
     *
     * <p>
     * This method is intended for cases where the provided ISBN-10 number possibly has an invalid checksum digit.
     * If the input ISBN-10 number is structurally valid and has an invalid checksum, a valid checksum digit
     * will be generated and replaced with the last digit of the ISBN-10 number, forming a complete and valid ISBN-10 number.
     * </p>.
     *
     * @param isbn The ISBN-10 number to validate and complete.
     * @return A valid ISBN-10 number (String) with the correct checksum digit, or {@code null} if the input ISBN-10 number is not valid.
     * @throws NullISBNException  If the input ISBN-10 number is null.
     * @throws EmptyISBNException if the input ISBN-10 number is an empty string.
     * @since 1.1.0
     */
    public String validateISBN10(String isbn) {
        if (isNullOrEmpty(isbn)) {
            return null;
        }

        String cleanISBN = generateCleanISBN(isbn);

        if (!ISBN10_PATTERN.matcher(cleanISBN).matches()) {
            return null;
        }

        return replaceWithValidChecksum(isbn, 10);
    }

    /**
     * Returns a valid ISBN-13 number, assuming every digit before checksum (last digit) is valid.
     *
     * <p>
     * This method is intended for cases where the provided ISBN-13 number possibly has an invalid checksum digit.
     * If the input ISBN-13 number is structurally valid and has an invalid checksum, a valid checksum digit
     * will be generated and replaced with the last digit of the ISBN-13 number, forming a complete and valid ISBN-13 number.
     * </p>
     *
     * @param isbn The ISBN-13 number to validate and complete.
     * @return A valid ISBN-13 number (String) with the correct checksum digit, or {@code null} if the input ISBN-13 number is not valid.
     * @throws NullISBNException  If the input ISBN-13 number is null.
     * @throws EmptyISBNException if the input ISBN-13 number is an empty string.
     * @since 1.1.0
     */
    public String validateISBN13(String isbn) {
        if (isNullOrEmpty(isbn)) {
            return null;
        }

        String cleanISBN = generateCleanISBN(isbn);

        if (!ISBN13_PATTERN.matcher(cleanISBN).matches()) {
            return null;
        }

        return replaceWithValidChecksum(isbn, 13);
    }

    // Helper function to replace the last character of the ISBN with the valid checksum digit.
    private String replaceWithValidChecksum(String isbn, int format) {
        String cleanIsbn = isbn.replaceAll("[^0-9]", "");
        String valid;

        if (format == 10) {
            valid = ISBN10Checksum.getInstance().calculate(cleanIsbn.substring(0, cleanIsbn.length() - 1));
        } else {
            valid = ISBN13Checksum.getInstance().calculate(cleanIsbn.substring(0, cleanIsbn.length() - 1));
        }

        if (lastChar(valid) == lastChar(isbn)) {
            return isbn;
        }
        return isbn.substring(0, isbn.length() - 1) + lastChar(valid);
    }

    /**
     * Checks if an ISBN is null or an empty string.
     *
     * @param isbn The ISBN to check.
     * @return {@code true} if the ISBN is null or an empty string, {@code false} otherwise.
     * @throws NullISBNException  if the ISBN is null or an empty string.
     * @throws EmptyISBNException if the ISBN is an empty string.
     * @since 1.1.0
     */
    @Override
    public boolean isNullOrEmpty(String isbn) {
        if (isbn == null) {
            throw new NullISBNException("The ISBN cannot be null");
        }
        if (isbn.trim().isEmpty()) {
            throw new EmptyISBNException("The ISBN cannot be an empty string");
        }

        return false;
    }

    // Helper function to get the last character of a string.
    private char lastChar(String input) {
        return input.charAt(input.length() - 1);
    }

    // Helper function to remove anything other than digits and generate a clean isbn.
    private String generateCleanISBN(String isbn) {
        String cleanISBN;
        if (isbn.toUpperCase().startsWith("ISBN-10") || isbn.toUpperCase().startsWith("ISBN-13")) {
            cleanISBN = isbn.substring(7);
        } else {
            cleanISBN = isbn;
        }

        cleanISBN = cleanISBN.replaceAll("[^0-9X]", "");
        return cleanISBN;
    }

}