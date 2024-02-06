package com.checkmate.validation.email;

import com.checkmate.exceptions.email.EmptyEmailException;
import com.checkmate.exceptions.email.NullEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void testValidEmail() {
        assertTrue(EmailValidator.getInstance().isValid("valid.email@example.com"));
    }

    @Test
    void testAnotherValidEmail() {
        assertTrue(EmailValidator.getInstance().isValid("another.valid.email@example.co.uk"));
    }

    @Test
    void testEmailWithNumbers() {
        assertTrue(EmailValidator.getInstance().isValid("user123@example.com"));
    }

    @Test
    void testEmailWithSpecialCharacters() {
        assertTrue(EmailValidator.getInstance().isValid("user$%&@example.com"));
    }

    @Test
    void testEmailWithSubdomain() {
        assertTrue(EmailValidator.getInstance().isValid("user@sub.example.com"));
    }

    @Test
    void testInvalidEmailNoAtSymbol() {
        assertFalse(EmailValidator.getInstance().isValid("invalid.email.com"));
    }

    @Test
    void testInvalidEmailNoDomain() {
        assertFalse(EmailValidator.getInstance().isValid("invalid.email@"));
    }

    @Test
    void testInvalidEmailNoUsername() {
        assertFalse(EmailValidator.getInstance().isValid("@example.com"));
    }

    @Test
    void testNullJMBG() {
        assertThrows(NullEmailException.class, () -> EmailValidator.getInstance().isValid(null));
    }

    @Test
    void testEmptyJMBG() {
        assertThrows(EmptyEmailException.class, () -> EmailValidator.getInstance().isValid(""));
    }
}