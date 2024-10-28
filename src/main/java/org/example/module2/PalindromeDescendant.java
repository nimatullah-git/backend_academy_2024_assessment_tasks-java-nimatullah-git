package org.example.module2;

public class PalindromeDescendant {
    public static boolean isPalindrome(String number) {
        int length = number.length();
        for (int i = 0; i < length / 2; i++) {
            if (number.charAt(i) != number.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static String createDescendant(String number) {
        StringBuilder descendant = new StringBuilder();
        for (int i = 0; i < number.length() - 1; i += 2) {
            int sum = Character.getNumericValue(number.charAt(i)) + Character.getNumericValue(number.charAt(i + 1));
            descendant.append(sum);
        }
        return descendant.toString();
    }

    public static boolean isPalindromeDescendant(int num) {
        String number = String.valueOf(num);

        while (number.length() > 1) {
            if (isPalindrome(number)) {
                return true;
            }

            number = createDescendant(number);
        }

        return false;
    }
}
