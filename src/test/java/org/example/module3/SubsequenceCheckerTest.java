package org.example.module3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubsequenceCheckerTest {

    @Test
    void givenSubsequence_whenChecking_thenReturnsTrue() {
        String s = "abc";
        String t = "achfdbaabgabcaabg";

        boolean result = SubsequenceChecker.isSubsequence(s, t);

        assertThat(result).isTrue();
    }

    @Test
    void givenNonSubsequence_whenChecking_thenReturnsFalse() {
        String s = "xyz";
        String t = "achfdbaabgabcaabg";

        boolean result = SubsequenceChecker.isSubsequence(s, t);

        assertThat(result).isFalse();
    }

    @Test
    void givenEmptySubsequence_whenChecking_thenReturnsTrue() {
        String s = "";
        String t = "achfdbaabgabcaabg";

        boolean result = SubsequenceChecker.isSubsequence(s, t);

        assertThat(result).isTrue();
    }

    @Test
    void givenEmptyMainString_whenCheckingNonEmptySubsequence_thenReturnsFalse() {
        String s = "abc";
        String t = "";

        boolean result = SubsequenceChecker.isSubsequence(s, t);

        assertThat(result).isFalse();
    }

    @Test
    void givenBothEmptyStrings_whenChecking_thenReturnsTrue() {
        String s = "";
        String t = "";

        boolean result = SubsequenceChecker.isSubsequence(s, t);

        assertThat(result).isTrue();
    }
}
