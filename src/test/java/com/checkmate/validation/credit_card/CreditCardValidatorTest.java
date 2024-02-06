package com.checkmate.validation.credit_card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditCardValidatorTest {

    private static final String VISA_APPROVED = "4111-1111-1111-1111";
    private static final String VISA_APPROVED_2 = "4242-4242-4242-4242";
    private static final String VISA_APPROVED_3 = "4999-9999-9999-9103";
    private static final String VISA_FAILS_LUHN_CHECK_1 = "4111-1111-1111-1119";
    private static final String VISA_FAILS_LUHN_CHECK_2 = "4999-9999-9999-9108";
    private static final String VISA_FAILS_LUHN_CHECK_3 = "4999-9999-9999-9109";

    private static final String MASTERCARD_APPROVED = "5431-1111-1111-1111";
    private static final String MASTERCARD_APPROVED_2 = "5123-4558-0630-8521";
    private static final String MASTERCARD_APPROVED_3 = "5123-4590-4605-8920";
    private static final String MASTERCARD_APPROVED_4 = "5427-6600-6424-1339";
    private static final String MASTERCARD_APPROVED_5 = "2223-0000-1000-0005";
    private static final String MASTERCARD_FAILS_LUHN_CHECK = "5999-9999-9999-9108";

    private static final String AMEX_APPROVED = "3774-0011-1111-115";
    private static final String AMEX_APPROVED_2 = "3760-0000-0000-006";
    private static final String AMEX_APPROVED_3 = "3711-1111-1111-114";
    private static final String AMEX_APPROVED_4 = "3712-3480-6987-034";

    private static final String DINERS_APPROVED = "3600-0000-0000-08";
    private static final String DINERS_APPROVED_2 = "3612-3404-8703-94";
    private static final String DINERS_APPROVED_3 = "3612-3409-8536-19";
    private static final String DINERS_APPROVED_4 = "3612-3442-2696-33";

    private static final String DISCOVER_APPROVED = "6011-1111-1111-1117";
    private static final String DISCOVER_APPROVED_2 = "6601-1111-1111-1113";
    private static final String DISCOVER_APPROVED_3 = "6011-3099-0000-1248";

    private static final String JCB_APPROVED = "3562-3500-0000-0003";

    private static final String ASB_TRUE_REWARDS_APPROVED = "5000-5111-1111-1113";

    private static final String SWITCH_APPROVED = "4903-1111-1111-1113";

    private static final String VISA_ELECTRON_APPROVED = "4026-1111-1111-1115";

    private static final String WAREHOUSE_MONEY_VISA_CARD_APPROVED = "4065-9301-0900-0002";

    @Test
    void testVisaApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(VISA_APPROVED), "Visa Approved");
    }

    @Test
    void testVisaApproved2() {
        assertTrue(CreditCardValidator.getInstance().isValid(VISA_APPROVED_2), "Visa Approved");
    }

    @Test
    void testVisaApproved3() {
        assertTrue(CreditCardValidator.getInstance().isValid(VISA_APPROVED_3), "Visa Approved");
    }

    @Test
    void testVisaFailsLuhnCheck1() {
        assertFalse(CreditCardValidator.getInstance().isValid(VISA_FAILS_LUHN_CHECK_1), "Visa Fails Luhn Check");
    }

    @Test
    void testVisaFailsLuhnCheck2() {
        assertFalse(CreditCardValidator.getInstance().isValid(VISA_FAILS_LUHN_CHECK_2), "Visa Fails Luhn Check");
    }

    @Test
    void testVisaFailsLuhnCheck3() {
        assertFalse(CreditCardValidator.getInstance().isValid(VISA_FAILS_LUHN_CHECK_3), "Visa Fails Luhn Check");
    }

    @Test
    void testMasterCardApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(MASTERCARD_APPROVED), "MasterCard Approved");
    }

    @Test
    void testMasterCardApproved2() {
        assertTrue(CreditCardValidator.getInstance().isValid(MASTERCARD_APPROVED_2), "MasterCard Approved");
    }

    @Test
    void testMasterCardApproved3() {
        assertTrue(CreditCardValidator.getInstance().isValid(MASTERCARD_APPROVED_3), "MasterCard Approved");
    }

    @Test
    void testMasterCardApproved4() {
        assertTrue(CreditCardValidator.getInstance().isValid(MASTERCARD_APPROVED_4), "MasterCard Approved");
    }

    @Test
    void testMasterCardApproved5() {
        assertTrue(CreditCardValidator.getInstance().isValid(MASTERCARD_APPROVED_5), "MasterCard Approved");
    }


    @Test
    void testMasterCardFailsLuhnCheck() {
        assertFalse(CreditCardValidator.getInstance().isValid(MASTERCARD_FAILS_LUHN_CHECK), "MasterCard Fails Luhn Check");
    }

    @Test
    void testAmexApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(AMEX_APPROVED), "Amex Approved");
    }

    @Test
    void testAmexApproved2() {
        assertTrue(CreditCardValidator.getInstance().isValid(AMEX_APPROVED_2), "Amex Approved");
    }

    @Test
    void testAmexApproved3() {
        assertTrue(CreditCardValidator.getInstance().isValid(AMEX_APPROVED_3), "Amex Approved");
    }

    @Test
    void testAmexApproved4() {
        assertTrue(CreditCardValidator.getInstance().isValid(AMEX_APPROVED_4), "Amex Approved");
    }

    @Test
    void testDinersApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(DINERS_APPROVED), "Diners Approved");
    }

    @Test
    void testDinersApproved2() {
        assertTrue(CreditCardValidator.getInstance().isValid(DINERS_APPROVED_2), "Diners Approved");
    }

    @Test
    void testDinersApproved3() {
        assertTrue(CreditCardValidator.getInstance().isValid(DINERS_APPROVED_3), "Diners Approved");
    }

    @Test
    void testDinersApproved4() {
        assertTrue(CreditCardValidator.getInstance().isValid(DINERS_APPROVED_4), "Diners Approved");
    }

    @Test
    void testDiscoverApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(DISCOVER_APPROVED), "Discover Approved");
    }

    @Test
    void testDiscoverApproved2() {
        assertTrue(CreditCardValidator.getInstance().isValid(DISCOVER_APPROVED_2), "Discover Approved");
    }

    @Test
    void testDiscoverApproved3() {
        assertTrue(CreditCardValidator.getInstance().isValid(DISCOVER_APPROVED_3), "Discover Approved");
    }

    @Test
    void testJcbApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(JCB_APPROVED), "JCB Approved");
    }

    @Test
    void testAsbTrueRewardsApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(ASB_TRUE_REWARDS_APPROVED), "ASB True Rewards Approved");
    }

    @Test
    void testSwitchApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(SWITCH_APPROVED), "Switch Approved");
    }

    @Test
    void testVisaElectronApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(VISA_ELECTRON_APPROVED), "Visa Electron Approved");
    }

    @Test
    void testWarehouseMoneyVisaCardApproved() {
        assertTrue(CreditCardValidator.getInstance().isValid(WAREHOUSE_MONEY_VISA_CARD_APPROVED), "Warehouse Money Visa Card Approved");
    }

}