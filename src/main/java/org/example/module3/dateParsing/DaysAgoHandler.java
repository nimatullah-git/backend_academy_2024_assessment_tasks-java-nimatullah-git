package org.example.module3.dateParsing;

import java.time.LocalDate;
import java.util.Optional;

class DaysAgoHandler extends DateHandler {
    @Override
    protected Optional<LocalDate> parse(String input) {
        if (input.matches("\\d+ days? ago")) {
            int daysAgo = Integer.parseInt(input.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(daysAgo));
        }
        return Optional.empty();
    }
}
