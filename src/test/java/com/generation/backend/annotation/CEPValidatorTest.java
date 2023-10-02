package com.generation.backend.annotation;

import com.generation.backend.annotation.validator.CEPValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class CEPValidatorTest {

    private CEPValidator cepValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        cepValidator = new CEPValidator();
        context = null;
    }

    @AfterEach
    void tearDown() {
        cepValidator = null;
        context = null;
    }

    @Test
    void initialize() {
        assertDoesNotThrow(() -> cepValidator.initialize(null));
    }

    @Test
    void isValid_validCEP_shouldReturnTrue() {
        String validCEP = "13082-205";

        boolean isValid = cepValidator.isValid(validCEP, context);
    }
}
