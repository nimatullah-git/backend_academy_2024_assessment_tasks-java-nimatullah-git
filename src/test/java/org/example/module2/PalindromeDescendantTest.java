package org.example.module2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeDescendantTest {
    @Test
    public void testIsPalindromeDescendant() {
        assertTrue(PalindromeDescendant.isPalindromeDescendant(11211230));
        assertTrue(PalindromeDescendant.isPalindromeDescendant(13001120));
        assertTrue(PalindromeDescendant.isPalindromeDescendant(23336014));
        assertTrue(PalindromeDescendant.isPalindromeDescendant(11));
    }

    @Test
    public void testIsNotPalindromeDescendant() {
        assertFalse(PalindromeDescendant.isPalindromeDescendant(123456));
        assertFalse(PalindromeDescendant.isPalindromeDescendant(987654321));
    }

    @Test
    public void testSingleDigitNumber() {
        assertFalse(PalindromeDescendant.isPalindromeDescendant(9));
    }
}
