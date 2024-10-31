package org.example.module3.dateParsing;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    public Optional<LocalDate> parseDate(String input) {
        DateHandler handler = new ISODateHandler();
        handler
                .setNext(new SlashDateHandler())
                .setNext(new RelativeDateHandler())
                .setNext(new DaysAgoHandler());

        return handler.handle(input);
    }
}
