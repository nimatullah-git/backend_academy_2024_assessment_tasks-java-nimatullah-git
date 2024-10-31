package org.example.module3;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SessionAnalytics {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    public static Duration calculateAverageSessionDuration(List<String> sessionStrings) {
        Duration totalDuration = Duration.ZERO;

        for (String session : sessionStrings) {
            String[] parts = session.split(" - ");
            LocalDateTime start = LocalDateTime.parse(parts[0], FORMATTER);
            LocalDateTime end = LocalDateTime.parse(parts[1], FORMATTER);
            totalDuration = totalDuration.plus(Duration.between(start, end));
        }

        long averageMinutes = totalDuration.toMinutes() / sessionStrings.size();
        return Duration.ofMinutes(averageMinutes);
    }
}
