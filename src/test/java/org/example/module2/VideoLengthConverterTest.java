package org.example.module2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoLengthConverterTest {
    @Test
    public void testValidTime() {
        assertEquals(60, VideoLengthConverter.minutesToSeconds("01:00"));
        assertEquals(836, VideoLengthConverter.minutesToSeconds("13:56"));
        assertEquals(59999, VideoLengthConverter.minutesToSeconds("999:59"));
    }

    @Test
    public void testInvalidSeconds() {
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("10:60"));
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("05:61"));
    }

    @Test
    public void testInvalidFormat() {
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("10"));
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("10:"));
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("abc:44"));
    }

    @Test
    public void testNegativeValues() {
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("-10:30"));
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("10:-30"));
        assertEquals(-1, VideoLengthConverter.minutesToSeconds("-5:-15"));
    }
}
