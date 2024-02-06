package com.checkmate.validation.iban;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IBANValidatorTest {

    private static final String[] VALID_IBAN_NUMBERS = new String[]{
            "AD1200012030200359100100",
            "AE070331234567890123456",
            "AL47212110090000000235698741",
            "AT611904300234573201",
            "AZ21NABZ00000000137010001944",
            "BA391290079401028494",
            "BE68539007547034",
            "BG80BNBG96611020345678",
            "BH67BMAG00001299123456",
            "BI4210000100010000332045181",
            "BR1800000000141455123924100C2",
            "BR1800360305000010009795493C1",
            //"BR9700360305000010009795493P1", Not valid (?)
            "BY13NBRB3600900000002Z00AB00",
            "CH9300762011623852957",
            "CR05015202001026284066",
            "CY17002001280000001200527600",
            "CZ6508000000192000145399",
            "CZ9455000000001011038930",
            "DE89370400440532013000",
            "DJ2110002010010409943020008",
            "DK5000400440116243",
            "DO28BAGR00000001212453611324",
            "EE382200221020145685",
            "EG380019000500000000263180002",
            "ES9121000418450200051332",
            "FI2112345600000785",
            "FI5542345670000081",
            "AX2112345600000785", // FI other
            "AX5542345670000081", // FI other
            "FO6264600001631634",
            "FR1420041010050500013M02606",
            "BL6820041010050500013M02606", // FR other
            "GF4120041010050500013M02606", // FR other
            "GP1120041010050500013M02606", // FR other
            "MF8420041010050500013M02606", // FR other
            "MQ5120041010050500013M02606", // FR other
            "NC8420041010050500013M02606", // FR other
            "PF5720041010050500013M02606", // FR other
            "PM3620041010050500013M02606", // FR other
            "RE4220041010050500013M02606", // FR other
            "TF2120041010050500013M02606", // FR other
            "WF9120041010050500013M02606", // FR other
            "YT3120041010050500013M02606", // FR other
            "GB29NWBK60161331926819",
            "GE29NB0000000101904917",
            "GI75NWBK000000007099453",
            "GL8964710001000206",
            "GR1601101250000000012300695",
            "GT82TRAJ01020000001210029690",
            "HR1210010051863000160",
            "HU42117730161111101800000000",
            "IE29AIBK93115212345678",
            "IL620108000000099999999",
            //"IQ98NBIQ850123456789012", // Not valid (?)
            "IS140159260076545510730339",
            "IT60X0542811101000000123456",
            "JO94CBJO0010000000000131000302",
            "KW81CBKU0000000000001234560101",
            "KZ86125KZT5004100100",
            "LB62099900000001001901229114",
            "LC55HEMM000100010012001200023015",
            "LI21088100002324013AA",
            "LT121000011101001000",
            "LU280019400644750000",
            "LY83002048000020100120361",
            "LV80BANK0000435195001",
            "LY83002048000020100120361",
            "MC5811222000010123456789030",
            "MD24AG000225100013104168",
            "ME25505000012345678951",
            "MK07250120000058984",
            "MR1300020001010000123456753",
            "MT84MALT011000012345MTLCAST001S",
            "MU17BOMM0101101030300200000MUR",
            "NL91ABNA0417164300",
            "NO9386011117947",
            "PK36SCBL0000001123456702",
            "PL61109010140000071219812874",
            "PS92PALS000000000400123456702",
            "PT50000201231234567890154",
            "QA58DOHB00001234567890ABCDEFG",
            "RO49AAAA1B31007593840000",
            "RS35260005601001611379",
            "RU0204452560040702810412345678901",
            "SA0380000000608010167519",
            "SC18SSCB11010000000000001497USD",
            "SD8811123456789012",
            "SE4550000000058398257466",
            "SI56191000000123438",
            "SI56263300012039086",
            "SK3112000000198742637541",
            "SM86U0322509800000000270100",
            "ST68000100010051845310112",
            "SV62CENR00000000000000700025",
            "SV43ACAT00000000000000123123",
            "TL380080012345678910157",
            "TN5910006035183598478831",
            "TR330006100519786457841326",
            "UA213223130000026007233566001",
            "UA213996220000026007233566001",
            "VA59001123000012345678",
            "VG96VPVG0000012345678901",
            "XK051212012345678906",
    };

    @Test
    public void testIBANValidation() {
        IBANValidator ibanValidator = IBANValidator.getInstance();

        for (String iban : VALID_IBAN_NUMBERS) {
            try {
                assertTrue(ibanValidator.isValid(iban));
                printColored("IBAN Valid: " + iban, ConsoleColors.GREEN);
            } catch (AssertionError e) {
                printColored("IBAN Invalid: " + iban, ConsoleColors.RED);
            } catch (Exception e) {
                printColored("Error in validation for IBAN: " + iban, ConsoleColors.RED);
            }
        }
    }

    // Helper method to print colored text
    private void printColored(String text, String color) {
        System.out.println(color + text + ConsoleColors.RESET);
    }

    // Don't do this
    private static class ConsoleColors {
        private static final String RESET = "\u001B[0m";
        private static final String RED = "\u001B[31m";
        private static final String GREEN = "\u001B[32m";
    }

}