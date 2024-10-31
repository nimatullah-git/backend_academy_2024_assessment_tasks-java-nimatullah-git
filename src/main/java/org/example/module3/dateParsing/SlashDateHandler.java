package org.example.module3.dateParsing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

class SlashDateHandler extends DateHandler {
    private static final DateTimeFormatter SLASH_FORMATTER_4_DIGIT = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter SLASH_FORMATTER_2_DIGIT = DateTimeFormatter.ofPattern("M/d/yy");

    @Override
    protected Optional<LocalDate> parse(String input) {
        try {
            return Optional.of(LocalDate.parse(input, SLASH_FORMATTER_4_DIGIT));
        } catch (DateTimeParseException e) {
            try {
                return Optional.of(LocalDate.parse(input, SLASH_FORMATTER_2_DIGIT));
            } catch (DateTimeParseException ignored) {
                return Optional.empty();
            }
        }
    }
}
