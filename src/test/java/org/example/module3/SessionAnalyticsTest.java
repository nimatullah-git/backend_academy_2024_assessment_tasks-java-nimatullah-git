package org.example.module3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SessionAnalyticsTest {
    @Test
    @DisplayName("Calculate average session duration")
    void calculateAverageSessionDuration_GivenSessionStrings_ShouldReturnCorrectAverageDuration() {
        List<String> sessions = List.of(
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        Duration averageDuration = SessionAnalytics.calculateAverageSessionDuration(sessions);
        assertThat(averageDuration).isEqualTo(Duration.ofHours(3).plusMinutes(40));
    }
}
