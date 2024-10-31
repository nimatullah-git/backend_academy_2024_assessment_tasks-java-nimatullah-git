package org.example.module3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordValidatorTest {
    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void givenPasswordWithRequiredSymbol_whenIsValidPassword_thenReturnsTrue() {
        String password = "Passw0rd!";

        boolean result = validator.isValidPassword(password);

        assertThat(result).isTrue();
    }

    @Test
    void givenPasswordWithoutRequiredSymbol_whenIsValidPassword_thenReturnsFalse() {
        String password = "Password123";

        boolean result = validator.isValidPassword(password);

        assertThat(result).isFalse();
    }

    @Test
    void givenPasswordWithMultipleRequiredSymbols_whenIsValidPassword_thenReturnsTrue() {
        String password = "S3cur3P@ssw*rd";

        boolean result = validator.isValidPassword(password);

        assertThat(result).isTrue();
    }

    @Test
    void givenEmptyPassword_whenIsValidPassword_thenReturnsFalse() {
        String password = "";

        boolean result = validator.isValidPassword(password);

        assertThat(result).isFalse();
    }
}
