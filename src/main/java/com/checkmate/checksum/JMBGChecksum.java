package com.checkmate.checksum;

/**
 * The JMBGChecksum class provides a method for generating a check-digit of a JMBG number.
 *
 * @since 1.1.0-alpha
 */
public class JMBGChecksum extends Checksum {

    private static final JMBGChecksum instance = new JMBGChecksum();

    /**
     * Gets the singleton instance of JMBGChecksum.
     *
     * @return The singleton instance of JMBGChecksum.
     */
    public static JMBGChecksum getInstance() {
        return instance;
    }

    /**
     * Default constructor for the JMBGChecksum class
     */
    public JMBGChecksum() {
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
        return "0123456789";
    }

    @Override
    protected boolean hasTwoCheckDigits() {
        return false;
    }


    /**
     * Validates the given JMBG against the JMBG checksum algorithm.
     *
     * @param jmbg The JMBG to be validated.
     * @return {@code true} if the JMBG is valid according to the checksum algorithm, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String jmbg) {
        int numOfDigits = hasTwoCheckDigits() ? 2 : 1;
        String stringWithoutChecksum = jmbg.substring(0, jmbg.length() - numOfDigits);

        return jmbg.equals(calculate(stringWithoutChecksum));
    }

    /**
     * Gets the checksum of the JMBG.
     *
     * @param jmbg The JMBG for which the check-digit will be generated.
     * @return The singleton instance of JMBGChecksum.
     */
    public String calculate(String jmbg) {
        int sum = 0;
        int m = getModulus();
        int r = getRadix();
        for (int i = 0; i < 6; i++) {
            sum += (7 - i) * (Character.getNumericValue(jmbg.charAt(i)) + Character.getNumericValue(jmbg.charAt(i + 6)));
        }
        return jmbg + (char) ('0' + (m - (sum % m)) % r);
    }

}