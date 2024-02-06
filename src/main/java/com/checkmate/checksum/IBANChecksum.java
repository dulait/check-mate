package com.checkmate.checksum;

/**
 * <p>
 * The IBANChecksum class provides methods for generating and validating the checksum of an IBAN (International Bank Account Number).
 * </p>
 * <p>
 * Note that <b>not all</b> IBAN numbers correspond to the algorithms used by this class.<br>
 * More information about the algorithms that can be used for validating IBAN numbers can be found
 * <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number#Algorithms">here</a>.
 * </p>
 *
 * @since 1.1.0-alpha
 */
public class IBANChecksum extends Checksum {

    private static final IBANChecksum instance = new IBANChecksum();

    /**
     * Gets the singleton instance of IBANChecksum.
     *
     * @return The singleton instance of IBANChecksum.
     */
    public static IBANChecksum getInstance() {
        return instance;
    }

    /**
     * Default constructor for the IBANChecksum class
     */
    public IBANChecksum() {
    }

    @Override
    protected int getModulus() {
        return 97;
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
        return true;
    }

    /**
     * Validates the given IBAN using the <a href="https://wiki.freepascal.org/ISO_7064">ISO-7064 MOD 97-10</a> algorithm.
     *
     * @param iban The IBAN to be validated.
     * @return {@code true} if the IBAN is valid according to the checksum algorithm, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String iban) {
        iban = transformIBAN(iban);
        int numOfDigits = hasTwoCheckDigits() ? 2 : 1;

        String stringWithoutChecksum = iban.substring(0, iban.length() - numOfDigits);

        return iban.equals(calculate(stringWithoutChecksum));
    }

    /**
     * Calculates the checksum for the given IBAN.
     *
     * @param iban The IBAN for which the checksum will be generated.
     * @return The complete IBAN with its calculated checksum.
     */
    private String calculate(String iban) {
        int p = 0;
        int m = getModulus();
        int r = getRadix();
        String charSet = getCharset();

        for (int i = 0; i < iban.length(); i++) {
            int val = charSet.indexOf(iban.charAt(i));
            p = ((p + val) * r) % m;
        }

        p = (p * r) % m;

        int checksum = (m - p + 1) % m;

        int second = checksum % r;
        int first = (checksum - second) / r;

        return iban + charSet.charAt(first) + charSet.charAt(second);
    }

    /**
     * Transforms the given IBAN by rearranging and converting it to a numeric string.
     *
     * <p>
     * More information on how IBANs are transformed can be found
     * <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number#Validating_the_IBAN">here</a>.
     * </p>
     *
     * @param iban The original IBAN.
     * @return The transformed numeric string.
     */
    private String transformIBAN(String iban) {
        String rearrangedIBAN = iban.replaceAll("\\s", "").substring(4) + iban.substring(0, 4);

        StringBuilder numericString = new StringBuilder();
        for (char c : rearrangedIBAN.toCharArray()) {
            if (Character.isLetter(c)) {
                numericString.append(Character.getNumericValue(Character.toUpperCase(c)));
            } else {
                numericString.append(c);
            }
        }

        return new String(numericString);
    }

}