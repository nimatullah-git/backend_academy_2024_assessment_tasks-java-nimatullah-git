package org.example.module3.dateParsing;

import java.time.LocalDate;
import java.util.Optional;

class RelativeDateHandler extends DateHandler {
    @Override
    protected Optional<LocalDate> parse(String input) {
        LocalDate today = LocalDate.now();

        return switch (input.toLowerCase()) {
            case "today" -> Optional.of(today);
            case "tomorrow" -> Optional.of(today.plusDays(1));
            case "yesterday" -> Optional.of(today.minusDays(1));
            default -> Optional.empty();
        };
    }
}
