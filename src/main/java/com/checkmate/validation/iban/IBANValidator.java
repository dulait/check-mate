package com.checkmate.validation.iban;

import com.checkmate.checksum.IBANChecksum;
import com.checkmate.exceptions.iban.EmptyIBANException;
import com.checkmate.exceptions.iban.NullIBANException;
import com.checkmate.validation.Validator;

import java.util.regex.Pattern;

/**
 * <p>
 * The IBANValidator class provides validations for IBAN (International Bank Account Numbers).
 * Since the IBAN uses a wide variety of ISO standards and algorithms, not all numbers can be validated using this class. <br>
 * <p>
 * The current version supports validation of numbers that use the {@code ISO-7064 MOD97-10} algorithms. <br>
 * A detailed list of countries and the algorithms that they use for validating the checksum digits can be found
 * <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number#National_check_digits">here</a>.
 * </p>
 *
 * @since 1.1.0
 */
public class IBANValidator implements Validator<String> {
    private static final IBANValidator instance = new IBANValidator();

    /**
     * Private constructor for the IBANValidator class
     */
    private IBANValidator() {
    }

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 34;
    private static final String[] COUNTRY_LIST = new String[]{
            "AD\\d{10}[A-Z0-9]{12}",
            "AD\\d{10}[A-Z0-9]{12}", // Andorra
            "AE\\d{21}", // United Arab Emirates (The)
            "AL\\d{10}[A-Z0-9]{16}", // Albania
            "AT\\d{18}", // Austria
            "AZ\\d{2}[A-Z]{4}[A-Z0-9]{20}", // Azerbaijan
            "BA\\d{18}", // Bosnia and Herzegovina
            "BE\\d{14}", // Belgium
            "BG\\d{2}[A-Z]{4}\\d{6}[A-Z0-9]{8}", // Bulgaria
            "BH\\d{2}[A-Z]{4}[A-Z0-9]{14}", // Bahrain
            "BI\\d{25}", // Burundi
            "BR\\d{25}[A-Z]{1}[A-Z0-9]{1}", // Brazil
            "BY\\d{2}[A-Z0-9]{4}\\d{4}[A-Z0-9]{16}", // Republic of Belarus
            "CH\\d{7}[A-Z0-9]{12}", // Switzerland
            "CR\\d{20}", // Costa Rica
            "CY\\d{10}[A-Z0-9]{16}", // Cyprus
            "CZ\\d{22}", // Czechia
            "DE\\d{20}", // Germany
            "DJ\\d{25}", // Djibouti
            "DK\\d{16}", // Denmark
            "DO\\d{2}[A-Z0-9]{4}\\d{20}", // Dominican Republic
            "EE\\d{18}", // Estonia
            "EG\\d{27}", // Egypt
            "ES\\d{22}", // Spain
            "(FI|AX)\\d{16}", // Finland & Associates.
            "FO\\d{16}", // Faroe Islands
            "(FR|BL|GF|GP|MF|MQ|NC|PF|PM|RE|TF|WF|YT)\\d{12}[A-Z0-9]{11}\\d{2}", // France & Associates.
            "GB\\d{2}[A-Z]{4}\\d{14}", // United Kingdom
            "GE\\d{2}[A-Z]{2}\\d{16}", // Georgia
            "GI\\d{2}[A-Z]{4}[A-Z0-9]{15}", // Gibraltar
            "GL\\d{16}", // Greenland
            "GR\\d{9}[A-Z0-9]{16}", // Greece
            "GT\\d{2}[A-Z0-9]{24}", // Guatemala
            "HR\\d{19}", // Croatia
            "HU\\d{26}", // Hungary
            "IE\\d{2}[A-Z]{4}\\d{14}", // Ireland
            "IL\\d{21}", // Israel
            "IQ\\d{2}[A-Z]{4}\\d{15}", // Iraq
            "IS\\d{24}", // Iceland
            "IT\\d{2}[A-Z]{1}\\d{10}[A-Z0-9]{12}", // Italy
            "JO\\d{2}[A-Z]{4}\\d{4}[A-Z0-9]{18}", // Jordan
            "KW\\d{2}[A-Z]{4}[A-Z0-9]{22}", // Kuwait
            "KZ\\d{5}[A-Z0-9]{13}", // Kazakhstan
            "LB\\d{6}[A-Z0-9]{20}", // Lebanon
            "LC\\d{2}[A-Z]{4}[A-Z0-9]{24}", // Saint Lucia
            "LI\\d{7}[A-Z0-9]{12}", // Liechtenstein
            "LT\\d{18}", // Lithuania
            "LU\\d{5}[A-Z0-9]{13}", // Luxembourg
            "LV\\d{2}[A-Z]{4}[A-Z0-9]{13}", // Latvia
            "LY\\d{23}", // Libya
            "MC\\d{12}[A-Z0-9]{11}\\d{2}", // Monaco
            "MD\\d{2}[A-Z0-9]{20}", // Moldova
            "ME\\d{20}", // Montenegro
            "MK\\d{5}[A-Z0-9]{10}\\d{2}", // Macedonia
            "MR\\d{25}", // Mauritania
            "MT\\d{2}[A-Z]{4}\\d{5}[A-Z0-9]{18}", // Malta
            "MU\\d{2}[A-Z]{4}\\d{19}[A-Z]{3}", // Mauritius
            "NL\\d{2}[A-Z]{4}\\d{10}", // Netherlands (The)
            "NO\\d{13}", // Norway
            "PK\\d{2}[A-Z]{4}[A-Z0-9]{16}", // Pakistan
            "PL\\d{26}", // Poland
            "PS\\d{2}[A-Z]{4}[A-Z0-9]{21}", // Palestine, State of
            "PT\\d{23}", // Portugal
            "QA\\d{2}[A-Z]{4}[A-Z0-9]{21}", // Qatar
            "RO\\d{2}[A-Z]{4}[A-Z0-9]{16}", // Romania
            "RS\\d{20}", // Serbia
            "RU\\d{31}", // Russia
            "SA\\d{4}[A-Z0-9]{18}", // Saudi Arabia
            "SC\\d{2}[A-Z]{4}\\d{20}[A-Z]{3}", // Seychelles
            "SD\\d{16}", // Sudan
            "SE\\d{22}", // Sweden
            "SI\\d{17}", // Slovenia
            "SK\\d{22}", // Slovakia
            "SM\\d{2}[A-Z]{1}\\d{10}[A-Z0-9]{12}", // San Marino
            "ST\\d{23}", // Sao Tome and Principe
            "SV\\d{2}[A-Z]{4}\\d{20}", // El Salvador
            "TL\\d{21}", // Timor-Leste
            "TN\\d{22}", // Tunisia
            "TR\\d{8}[A-Z0-9]{16}", // Turkey
            "UA\\d{8}[A-Z0-9]{19}", // Ukraine
            "VA\\d{20}", // Vatican City State
            "VG\\d{2}[A-Z]{4}\\d{16}", // Virgin Islands
            "XK\\d{18}" // Kosovo
    };

    /**
     * Gets the singleton instance of the IBANValidator.
     *
     * @return The singleton instance of the IBANValidator.
     */
    public static IBANValidator getInstance() {
        return instance;
    }


    /**
     * Checks if the given IBAN is valid. <br>
     *
     * @param iban The IBAN to validate.
     * @return {@code true} if the IBAN is valid, {@code false} otherwise.
     * @throws NullIBANException  if the input IBAN is null.
     * @throws EmptyIBANException if the input IBAN is an empty string.
     * @since 1.1.0
     */
    @Override
    public boolean isValid(String iban) {
        if (isNullOrEmpty(iban)) {
            return false;
        }
        if (iban.length() < MIN_LENGTH || iban.length() > MAX_LENGTH) {
            return false;
        }
        for (String countryRegex : COUNTRY_LIST) {
            iban = iban.replaceAll(" ", "");
            Pattern pattern = Pattern.compile(countryRegex);
            if (pattern.matcher(iban).matches()) {
                return IBANChecksum.getInstance().isValid(iban);
            }
        }

        return false;
    }

    /**
     * Checks if an IBAN is null or an empty string.
     *
     * @param iban The IBAN to check.
     * @return {@code true} if the IBAN is null or an empty string, {@code false} otherwise.
     * @throws NullIBANException  if the IBAN is null or an empty string.
     * @throws EmptyIBANException if the IBAN is an empty string.
     * @since 1.1.0
     */
    @Override
    public boolean isNullOrEmpty(String iban) {
        if (iban == null) {
            throw new NullIBANException("The IBAN cannot be null");
        }
        if (iban.trim().isEmpty()) {
            throw new EmptyIBANException("The IBAN cannot be an empty string");
        }
        return false;
    }

}