package org.example.module3;

import java.util.regex.Pattern;

public class LicensePlateValidator {

    private static final Pattern LICENSE_PLATE_PATTERN =
            Pattern.compile("^[А-Я]{1}[0-9]{3}[А-Я]{2}[0-9]{2}$");

    public boolean isValidLicensePlate(String plate) {
        return LICENSE_PLATE_PATTERN.matcher(plate).matches();
    }
}
