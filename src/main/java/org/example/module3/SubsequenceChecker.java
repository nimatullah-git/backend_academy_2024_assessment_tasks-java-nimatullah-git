package org.example.module3;

import java.util.regex.Pattern;

public class SubsequenceChecker {
    public static boolean isSubsequence(String s, String t) {
        String regex = ".*" + String.join(".*", s.split("")) + ".*";
        return Pattern.matches(regex, t);
    }
}
