package org.example.module2;

public class VideoLengthConverter {
    public static int minutesToSeconds(String time) {
        String[] parts = time.split(":");

        if (parts.length != 2) {
            return -1;
        }

        try {
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);

            if (minutes < 0 || seconds < 0 || seconds >= 60) {
                return -1;
            }

            return minutes * 60 + seconds;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
