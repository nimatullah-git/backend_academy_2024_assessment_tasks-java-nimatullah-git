package org.example.module3;

import java.util.regex.Pattern;

public class BinaryStringPatterns {

    public static boolean hasThirdCharZero(String s) {
        return Pattern.matches("^[01]{2}0[01]*$", s);
    }

    public static boolean startsAndEndsSame(String s) {
        return Pattern.matches("^(0.*0|1.*1)$", s);
    }

    public static boolean lengthBetweenOneAndThree(String s) {
        return Pattern.matches("^[01]{1,3}$", s);
    }
}
