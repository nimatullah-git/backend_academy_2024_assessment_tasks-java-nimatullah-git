package org.example.module3;

import org.example.module3.dateParsing.DateParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DateParserTest {
    private final DateParser parser = new DateParser();

    @Test
    void givenIsoDate_whenParse_thenReturnsCorrectDate() {
        String input = "2020-10-10";

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isPresent().contains(LocalDate.of(2020, 10, 10));
    }

    @Test
    void givenSlashDateWith4DigitYear_whenParse_thenReturnsCorrectDate() {
        String input = "1/3/1976";

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isPresent().contains(LocalDate.of(1976, 1, 3));
    }

    @Test
    void givenSlashDateWith2DigitYear_whenParse_thenReturnsCorrectDate() {
        String input = "1/3/20";

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isPresent().contains(LocalDate.of(2020, 1, 3));
    }

    @Test
    void givenTodayKeyword_whenParse_thenReturnsTodayDate() {
        String input = "today";
        LocalDate today = LocalDate.now();

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isPresent().contains(today);
    }

    @Test
    void givenTomorrowKeyword_whenParse_thenReturnsTomorrowDate() {
        String input = "tomorrow";
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isPresent().contains(tomorrow);
    }

    @Test
    void givenYesterdayKeyword_whenParse_thenReturnsYesterdayDate() {
        String input = "yesterday";
        LocalDate yesterday = LocalDate.now().minusDays(1);

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isPresent().contains(yesterday);
    }

    @Test
    void givenDaysAgoFormat_whenParse_thenReturnsCorrectDate() {
        String input = "2 days ago";
        LocalDate expectedDate = LocalDate.now().minusDays(2);

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isPresent().contains(expectedDate);
    }

    @Test
    void givenInvalidDateFormat_whenParse_thenReturnsEmptyOptional() {
        String input = "invalid date format";

        Optional<LocalDate> result = parser.parseDate(input);

        assertThat(result).isEmpty();
    }
}
