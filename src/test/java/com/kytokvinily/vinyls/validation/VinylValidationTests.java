package com.kytokvinily.vinyls.validation;

import com.kytokvinily.vinyls.dto.VinylDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;

public class VinylValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var vinyl = new VinylDto("title1", 1900, "author1");
        Set<ConstraintViolation<VinylDto>> violations = validator.validate(vinyl);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenAuthorNotDefinedThenValidationFails() {
        var vinyl = new VinylDto("title1", 1900, null);
        Set<ConstraintViolation<VinylDto>> violations = validator.validate(vinyl);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenYearNotMinimalThenValidationFails() {
        var vinyl = new VinylDto("title1", 1899, "author1");
        Set<ConstraintViolation<VinylDto>> violations = validator.validate(vinyl);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenTitleNotDefinedThenValidationFails() {
        var vinyl = new VinylDto(null, 1900, "author1");
        Set<ConstraintViolation<VinylDto>> violations = validator.validate(vinyl);
        assertThat(violations).hasSize(1);
    }
}
