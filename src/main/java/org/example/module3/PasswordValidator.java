package org.example.module3;

import java.util.regex.Pattern;

public class PasswordValidator {
    private static final Pattern REQUIRED_SYMBOLS_PATTERN = Pattern.compile("[~!@#$%^&*|]");

    public boolean isValidPassword(String password) {
        return REQUIRED_SYMBOLS_PATTERN.matcher(password).find();
    }
}
