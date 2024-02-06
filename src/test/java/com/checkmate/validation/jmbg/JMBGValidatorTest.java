package com.checkmate.validation.jmbg;

import com.checkmate.exceptions.jmbg.EmptyJMBGException;
import com.checkmate.exceptions.jmbg.NullJMBGException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JMBGValidatorTest {

    @Test
    void testValidJMBG() {
        assertTrue(JMBGValidator.getInstance().isValid("0101006500006"));
    }

    @Test
    void testInvalidJMBG() {
        assertFalse(JMBGValidator.getInstance().isValid("12345678901x3"));
    }

    @Test
    void testInvalidJMBGLength() {
        assertFalse(JMBGValidator.getInstance().isValid("123"));
    }

    @Test
    void testInvalidJMBGCharacters() {
        assertFalse(JMBGValidator.getInstance().isValid("abcdefghijk"));
    }

    @Test
    void testInvalidJMBGStrict() {
        assertFalse(JMBGValidator.getInstance().isValid("1234567890123"));
    }

    @Test
    void testNullJMBG() {
        assertThrows(NullJMBGException.class, () -> JMBGValidator.getInstance().isValid(null));
    }

    @Test
    void testEmptyJMBG() {
        assertThrows(EmptyJMBGException.class, () -> JMBGValidator.getInstance().isValid(""));
    }

}