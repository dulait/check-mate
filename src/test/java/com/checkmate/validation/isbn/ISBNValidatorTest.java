package com.checkmate.validation.isbn;

import com.checkmate.exceptions.isbn.EmptyISBNException;
import com.checkmate.exceptions.isbn.NullISBNException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ISBNValidatorTest {
    private static final String VALID_ISBN_13_1 = "ISBN 978-0-596-52068-7";
    private static final String VALID_ISBN_13_2 = "ISBN-13: 978-0-596-52068-7";
    private static final String VALID_ISBN_13_3 = "978 0 596 52068 7";
    private static final String VALID_ISBN_13_4 = "9780596520687";
    private static final String VALID_ISBN_10_1 = "ISBN-10 0-596-52068-9";
    private static final String VALID_ISBN_10_2 = "0-596-52068-9";

    @Test
    void givenISBN13ShouldReturnTrue1() {
        assertTrue(ISBNValidator.getInstance().isValid(VALID_ISBN_13_1));
    }

    @Test
    void givenISBN13ShouldReturnTrue2() {
        assertTrue(ISBNValidator.getInstance().isValid(VALID_ISBN_13_2));
    }

    @Test
    void givenISBN13ShouldReturnTrue3() {
        assertTrue(ISBNValidator.getInstance().isValid(VALID_ISBN_13_3));
    }

    @Test
    void givenISBN13ShouldReturnTrue4() {
        assertTrue(ISBNValidator.getInstance().isValid(VALID_ISBN_13_4));
    }

    @Test
    void givenISBN10ShouldReturnTrue1() {
        assertTrue(ISBNValidator.getInstance().isValid(VALID_ISBN_10_1));
    }

    @Test
    void givenISBN10ShouldReturnTrue2() {
        assertTrue(ISBNValidator.getInstance().isValid(VALID_ISBN_10_2));
    }

    @Test
    void givenISBNShouldThrowError1() {
        assertThrows(NullISBNException.class, () -> ISBNValidator.getInstance().isValid(null));
    }

    @Test
    void givenISBNShouldThrowError2() {
        assertThrows(EmptyISBNException.class, () -> ISBNValidator.getInstance().isValid(""));
    }

}