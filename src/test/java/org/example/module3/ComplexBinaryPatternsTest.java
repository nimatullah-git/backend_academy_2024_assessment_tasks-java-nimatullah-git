package org.example.module3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComplexBinaryPatternsTest {

    @Test
    void givenOddLength_whenChecking_thenReturnsTrue() {
        assertThat(ComplexBinaryPatterns.hasOddLength("101")).isTrue();
    }

    @Test
    void givenStartsWith0AndOddLengthOrStartsWith1AndEvenLength_whenChecking_thenReturnsTrue() {
        assertThat(ComplexBinaryPatterns.startsWith0OddOr1EvenLength("011")).isTrue();
    }

    @Test
    void givenZeroCountMultipleOfThree_whenChecking_thenReturnsTrue() {
        assertThat(ComplexBinaryPatterns.zeroCountMultipleOfThree("111000")).isTrue();
    }

    @Test
    void givenNot11or111_whenChecking_thenReturnsTrue() {
        assertThat(ComplexBinaryPatterns.not11or111("110")).isTrue();
    }

    @Test
    void givenEveryOddPositionIs1_whenChecking_thenReturnsTrue() {
        assertThat(ComplexBinaryPatterns.everyOddPositionIs1("101")).isTrue();
    }

    @Test
    void givenAtLeastTwo0AndAtMostOne1_whenChecking_thenReturnsTrue() {
        assertThat(ComplexBinaryPatterns.atLeastTwo0AndAtMostOne1("001")).isTrue();
    }

    @Test
    void givenNoConsecutive1_whenChecking_thenReturnsTrue() {
        assertThat(ComplexBinaryPatterns.noConsecutive1("1010")).isTrue();
    }
}
