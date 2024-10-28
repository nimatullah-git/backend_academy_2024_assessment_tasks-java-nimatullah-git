package org.example.module2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaprekarConstantTest {
    @Test
    public void testKaprekar() {
        assertEquals(3, KaprekarConstant.countK(3524));
        assertEquals(5, KaprekarConstant.countK(6621));
        assertEquals(4, KaprekarConstant.countK(6554));
        assertEquals(3, KaprekarConstant.countK(1234));
        assertEquals(0, KaprekarConstant.countK(6174));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(5, KaprekarConstant.countK(1000));
        assertEquals(3, KaprekarConstant.countK(9810));
    }

    @Test
    public void testInvalidInput() {
        assertEquals(-1, KaprekarConstant.countK(1111));
        assertEquals(-1, KaprekarConstant.countK(2222));
    }
}
