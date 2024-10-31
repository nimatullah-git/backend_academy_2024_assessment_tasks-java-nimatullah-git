package org.example.module3.dateParsing;

import java.time.LocalDate;
import java.util.Optional;

abstract class DateHandler {
    protected DateHandler next;

    public DateHandler setNext(DateHandler next) {
        this.next = next;
        return next;
    }

    public Optional<LocalDate> handle(String input) {
        Optional<LocalDate> result = parse(input);
        if (result.isPresent()) {
            return result;
        } else if (next != null) {
            return next.handle(input);
        } else {
            return Optional.empty();
        }
    }

    protected abstract Optional<LocalDate> parse(String input);
}
