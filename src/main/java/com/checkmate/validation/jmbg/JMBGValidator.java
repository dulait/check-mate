package com.checkmate.validation.jmbg;

import com.checkmate.checksum.JMBGChecksum;
import com.checkmate.exceptions.jmbg.EmptyJMBGException;
import com.checkmate.exceptions.jmbg.NullJMBGException;
import com.checkmate.validation.Validator;

import java.util.regex.Pattern;


/**
 * The JMBGValidator class provides methods for validating
 * and generating JMBG (Serbian Unique Master Citizen Number) numbers.
 *
 * @since @since 1.1.0
 */
public class JMBGValidator implements Validator<String> {
    private static final JMBGValidator instance = new JMBGValidator();

    /**
     * Private constructor for the JMBGValidator class
     */
    private JMBGValidator() {
    }

    private static final int LENGTH = 13;
    private static final String DAY_OF_BIRTH_REGEX = "(0[1-9]|[12][0-9]|3[01])";
    private static final String MONTH_OF_BIRTH_REGEX = "(0[1-9]|1[0-2])";
    private static final String YEAR_OF_BIRTH_REGEX = "(\\d){3}";
    private static final String POLITICAL_REGION_REGEX = "([0-9]{2})";
    private static final String UNIQUE_NUMBER_OF_POLITICAL_REGION_REGEX = "([0-9]{3})";
    private static final Pattern DAY_OF_BIRTH_PATTERN = Pattern.compile(DAY_OF_BIRTH_REGEX);
    private static final Pattern MONTH_OF_BIRTH_PATTERN = Pattern.compile(MONTH_OF_BIRTH_REGEX);
    private static final Pattern YEAR_OF_BIRTH_PATTERN = Pattern.compile(YEAR_OF_BIRTH_REGEX);
    private static final Pattern POLITICAL_REGION_PATTERN = Pattern.compile(POLITICAL_REGION_REGEX);
    private static final Pattern UNIQUE_NUMBER_OF_POLITICAL_REGION_PATTERN = Pattern.compile(UNIQUE_NUMBER_OF_POLITICAL_REGION_REGEX);

    /**
     * Gets the singleton instance of the JMBGValidator.
     *
     * @return The singleton instance of the JMBGValidator.
     */
    public static JMBGValidator getInstance() {
        return instance;
    }

    /**
     * <p>
     * Checks if the given JMBG satisfies all criteria. <br>
     * The criteria is the following:
     * </p>
     *     <ul>
     *          <li>The first and second digits represent the day of birth (1 - 31)</li>
     *          <li>The third and fourth digits represent the month of birth (1 - 12)</li>
     *          <li>The fifth, sixth and seventh digits represent the last 3 digits of the year of birth (ex. 001)</li>
     *          <li>The eighth and ninth digits represent the political region of birth (00 - 99)</li>
     *          <li>The tenth, eleventh and twelfth digits represent the gender (000-499 for M, 500 - 999 for F)</li>
     *       </ul>
     * <p>
     *     For more information on how the JMBG numbers are formed visit
     *     <a href="https://en.wikipedia.org/wiki/Unique_Master_Citizen_Number">the official Wikipedia page</a>
     * </p>
     *
     * @param jmbg The JMBG to validate.
     * @return {@code true} if the JMBG is valid, {@code false} otherwise.
     * @throws NullJMBGException  If the input JMBG is null.
     * @throws EmptyJMBGException if the input JMBG is an empty string.
     * @since 1.1.0
     */
    @Override
    public boolean isValid(String jmbg) {
        if (validate(jmbg) == null) {
            return false;
        }

        return jmbg.equals(JMBGChecksum.getInstance().calculate(jmbg.substring(0, 12)));
    }

    /**
     * <p>
     * Returns a valid JMBG, assuming every digit before checksum (last digit) is valid. <br>
     * This method should be used when the JMBG is structurally valid, but the checksum is not. <br>
     * </p>
     *
     * @param jmbg The JMBG to validate and complete.
     * @return A valid JMBG with the correct checksum digit, or {@code null} if the input JMBG is not valid.
     * @throws NullJMBGException  If the input JMBG is null.
     * @throws EmptyJMBGException if the input JMBG is an empty string.
     * @since 1.1.0
     */
    public String validate(String jmbg) {
        if (isNullOrEmpty(jmbg)) {
            return null;
        }

        if (jmbg.length() != LENGTH) {
            return null;
        }
        if (!substringsValid(jmbg)) {
            return null;
        }

        String valid = JMBGChecksum.getInstance().calculate(jmbg.substring(0, 12));
        if (!valid.equals(jmbg)) {
            return valid;
        }
        return jmbg;
    }

    /**
     * Checks if an JMBG is null or an empty string.
     *
     * @param jmbg The JMBG to check.
     * @return {@code true} if the JMBG is null or an empty string, {@code false} otherwise.
     * @throws NullJMBGException  if the JMBG is null or an empty string.
     * @throws EmptyJMBGException if the JMBG is an empty string.
     * @since 1.1.0
     */
    @Override
    public boolean isNullOrEmpty(String jmbg) {
        if (jmbg == null) {
            throw new NullJMBGException("JMBG cannot be null");
        }
        if (jmbg.trim().isEmpty()) {
            throw new EmptyJMBGException("JMBG cannot be empty");
        }
        return false;
    }

    // Helper function to check substrings of a JMBG.
    private boolean substringsValid(String jmbg) {
        String dd = jmbg.substring(0, 2);
        String mm = jmbg.substring(2, 4);
        String yyy = jmbg.substring(4, 7);
        String rr = jmbg.substring(7, 9);
        String bbb = jmbg.substring(9, 12);

        return DAY_OF_BIRTH_PATTERN.matcher(dd).matches() && MONTH_OF_BIRTH_PATTERN.matcher(mm).matches()
                && YEAR_OF_BIRTH_PATTERN.matcher(yyy).matches() && POLITICAL_REGION_PATTERN.matcher(rr).matches()
                && UNIQUE_NUMBER_OF_POLITICAL_REGION_PATTERN.matcher(bbb).matches();
    }


}