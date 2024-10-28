package org.example.module2;

import java.util.Arrays;

public class KaprekarConstant {
    public static int countK(int num) {
        return countK(num, 0);
    }

    private static int countK(int num, int steps) {
        if (num == 6174) {
            return steps;
        }

        if (steps >= 7) {
            return -1;
        }

        String numStr = String.format("%04d", num);

        char firstDigit = numStr.charAt(0);
        boolean allSame = true;
        for (int i = 1; i < numStr.length(); i++) {
            if (numStr.charAt(i) != firstDigit) {
                allSame = false;
                break;
            }
        }
        if (allSame) {
            return -1;
        }

        char[] ascDigits = numStr.toCharArray();
        Arrays.sort(ascDigits);
        String ascStr = new String(ascDigits);

        String descStr = new StringBuilder(ascStr).reverse().toString();

        int ascNum = Integer.parseInt(ascStr);
        int descNum = Integer.parseInt(descStr);

        int nextNum = descNum - ascNum;

        return countK(nextNum, steps + 1);
    }
}
