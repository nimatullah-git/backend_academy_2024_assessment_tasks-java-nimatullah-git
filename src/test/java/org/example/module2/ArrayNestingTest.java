package org.example.module2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayNestingTest {
    @Test
    public void testIsNestable() {
        assertTrue(ArrayNesting.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6}));
        assertTrue(ArrayNesting.isNestable(new int[]{3, 1}, new int[]{4, 0}));
        assertFalse(ArrayNesting.isNestable(new int[]{9, 9, 8}, new int[]{8, 9}));
        assertFalse(ArrayNesting.isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3}));
    }

    @Test
    public void testEmptyArrays() {
        assertFalse(ArrayNesting.isNestable(new int[]{}, new int[]{}));
        assertFalse(ArrayNesting.isNestable(new int[]{1, 2}, new int[]{}));
    }

    @Test
    public void testSameMinMax() {
        assertFalse(ArrayNesting.isNestable(new int[]{5, 5, 5}, new int[]{5, 5}));
        assertFalse(ArrayNesting.isNestable(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
    }

    @Test
    public void testNegativeValues() {
        assertTrue(ArrayNesting.isNestable(new int[]{-10, -8}, new int[]{-20, 0}));
        assertFalse(ArrayNesting.isNestable(new int[]{-5, -1}, new int[]{-6, -2}));
    }
}
