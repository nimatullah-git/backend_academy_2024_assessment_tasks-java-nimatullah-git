package org.example.module2;

import java.util.Arrays;

public class ArrayNesting {
    public static boolean isNestable(int[] a1, int[] a2) {
        int minA1 = Arrays.stream(a1).min().orElse(Integer.MAX_VALUE);
        int maxA1 = Arrays.stream(a1).max().orElse(Integer.MIN_VALUE);

        int minA2 = Arrays.stream(a2).min().orElse(Integer.MAX_VALUE);
        int maxA2 = Arrays.stream(a2).max().orElse(Integer.MIN_VALUE);

        return minA1 > minA2 && maxA1 < maxA2;
    }
}
