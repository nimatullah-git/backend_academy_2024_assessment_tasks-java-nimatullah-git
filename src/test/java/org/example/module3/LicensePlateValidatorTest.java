package org.example.module3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LicensePlateValidatorTest {
    private final LicensePlateValidator validator = new LicensePlateValidator();

    @Test
    void givenValidLicensePlate_whenIsValidLicensePlate_thenReturnsTrue() {
        String plate = "А123ВЕ77";

        boolean result = validator.isValidLicensePlate(plate);

        assertThat(result).isTrue();
    }

    @Test
    void givenAnotherValidLicensePlate_whenIsValidLicensePlate_thenReturnsTrue() {
        String plate = "О777ОО77";

        boolean result = validator.isValidLicensePlate(plate);

        assertThat(result).isTrue();
    }

    @Test
    void givenInvalidLicensePlateWithNumbersFirst_whenIsValidLicensePlate_thenReturnsFalse() {
        String plate = "123АВЕ77";

        boolean result = validator.isValidLicensePlate(plate);

        assertThat(result).isFalse();
    }

    @Test
    void givenInvalidLicensePlateWithTooFewCharacters_whenIsValidLicensePlate_thenReturnsFalse() {
        String plate = "А123ВГ77";

        boolean result = validator.isValidLicensePlate(plate);

        assertThat(result).isFalse();
    }

    @Test
    void givenInvalidLicensePlateWithTooManyCharacters_whenIsValidLicensePlate_thenReturnsFalse() {
        String plate = "А123ВЕ7777";

        boolean result = validator.isValidLicensePlate(plate);

        assertThat(result).isFalse();
    }
}
