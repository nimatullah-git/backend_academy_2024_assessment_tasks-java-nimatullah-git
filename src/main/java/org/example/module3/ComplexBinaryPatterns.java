package org.example.module3;

import java.util.regex.Pattern;

public class ComplexBinaryPatterns {

    public static boolean hasOddLength(String s) {
        return Pattern.matches("^(?:[01]{2})*[01]$", s);
    }

    public static boolean startsWith0OddOr1EvenLength(String s) {
        return Pattern.matches("^(0[01]*[01]$|1(?:[01]{2})*)$", s);
    }

    public static boolean zeroCountMultipleOfThree(String s) {
        return Pattern.matches("^(1*0{3})*1*$", s);
    }

    public static boolean not11or111(String s) {
        return Pattern.matches("^(?!11$|111$)[01]+$", s);
    }

    public static boolean everyOddPositionIs1(String s) {
        return Pattern.matches("^(1[01])*1?$", s);
    }

    public static boolean atLeastTwo0AndAtMostOne1(String s) {
        return Pattern.matches("^0*1?0*$", s);
    }

    public static boolean noConsecutive1(String s) {
        return Pattern.matches("^1?(0+1?)*$", s);
    }
}
