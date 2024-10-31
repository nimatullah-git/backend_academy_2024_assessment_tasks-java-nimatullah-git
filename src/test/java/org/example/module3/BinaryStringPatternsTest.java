package org.example.module3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryStringPatternsTest {

    @Test
    void givenStringWithThirdCharZero_whenChecking_thenReturnsTrue() {
        assertThat(BinaryStringPatterns.hasThirdCharZero("110")).isTrue();
        assertThat(BinaryStringPatterns.hasThirdCharZero("10010")).isTrue();
    }

    @Test
    void givenStringWithoutThirdCharZero_whenChecking_thenReturnsFalse() {
        assertThat(BinaryStringPatterns.hasThirdCharZero("111")).isFalse();
        assertThat(BinaryStringPatterns.hasThirdCharZero("1011")).isFalse();
    }

    @Test
    void givenStringStartsAndEndsSame_whenChecking_thenReturnsTrue() {
        assertThat(BinaryStringPatterns.startsAndEndsSame("00")).isTrue();
        assertThat(BinaryStringPatterns.startsAndEndsSame("1001")).isTrue();
    }

    @Test
    void givenStringDoesNotStartAndEndSame_whenChecking_thenReturnsFalse() {
        assertThat(BinaryStringPatterns.startsAndEndsSame("01")).isFalse();
        assertThat(BinaryStringPatterns.startsAndEndsSame("1000")).isFalse();
    }

    @Test
    void givenStringLengthBetweenOneAndThree_whenChecking_thenReturnsTrue() {
        assertThat(BinaryStringPatterns.lengthBetweenOneAndThree("0")).isTrue();
        assertThat(BinaryStringPatterns.lengthBetweenOneAndThree("101")).isTrue();
    }

    @Test
    void givenStringLengthOutOfRange_whenChecking_thenReturnsFalse() {
        assertThat(BinaryStringPatterns.lengthBetweenOneAndThree("")).isFalse();
        assertThat(BinaryStringPatterns.lengthBetweenOneAndThree("1001")).isFalse();
    }
}
