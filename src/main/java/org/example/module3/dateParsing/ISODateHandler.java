package org.example.module3.dateParsing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

class ISODateHandler extends DateHandler {
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d");

    @Override
    protected Optional<LocalDate> parse(String input) {
        try {
            return Optional.of(LocalDate.parse(input, ISO_FORMATTER));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
