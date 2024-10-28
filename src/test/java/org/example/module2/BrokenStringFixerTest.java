package org.example.module2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrokenStringFixerTest {
    @Test
    public void testFixString() {
        assertEquals("214365", BrokenStringFixer.fixString("123456"));
        assertEquals("This is a mixed up string.", BrokenStringFixer.fixString("hTsii  s aimex dpus rtni.g"));
        assertEquals("abcde", BrokenStringFixer.fixString("badce"));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", BrokenStringFixer.fixString(""));
    }

    @Test
    public void testSingleCharacter() {
        assertEquals("a", BrokenStringFixer.fixString("a"));
    }

    @Test
    public void testOddLengthString() {
        assertEquals("bac", BrokenStringFixer.fixString("abc"));
        assertEquals("2143657", BrokenStringFixer.fixString("1234567"));
    }

    @Test
    public void testSpecialCharacters() {
        assertEquals("!@$#^%*&", BrokenStringFixer.fixString("@!#$%^&*"));
    }
}
