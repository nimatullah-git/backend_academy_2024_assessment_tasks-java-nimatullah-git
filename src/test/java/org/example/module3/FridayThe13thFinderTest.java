package org.example.module3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FridayThe13thFinderTest {
    @Test
    @DisplayName("Find all Fridays 13th in a given year")
    void findFridays13InYear_GivenYear_ShouldReturnAllFridays13() {
        int year = 2024;
        List<LocalDate> fridays13 = FridayThe13thFinder.findFridays13InYear(year);
        assertThat(fridays13).containsExactly(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13));
    }

    @Test
    @DisplayName("Find the next Friday 13th after a given date")
    void findNextFriday13_GivenDate_ShouldReturnNextFriday13() {
        LocalDate date = LocalDate.of(2024, 10, 1);
        LocalDate nextFriday13 = FridayThe13thFinder.findNextFriday13(date);
        assertThat(nextFriday13).isEqualTo(LocalDate.of(2024, 12, 13));
    }
}
