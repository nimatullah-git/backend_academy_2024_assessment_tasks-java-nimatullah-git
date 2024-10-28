package org.example.module2;

public class BrokenStringFixer {
    public static String fixString(String input) {
        StringBuilder fixedString = new StringBuilder(input);

        for (int i = 0; i < input.length() - 1; i += 2) {
            char temp = fixedString.charAt(i);
            fixedString.setCharAt(i, fixedString.charAt(i + 1));
            fixedString.setCharAt(i + 1, temp);
        }

        return fixedString.toString();
    }
}
