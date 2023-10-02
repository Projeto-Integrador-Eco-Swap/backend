package com.generation.backend.annotation;

import com.generation.backend.annotation.validator.UFValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UFValidatorTest {

    private UFValidator ufValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        ufValidator = new UFValidator();
        context = null;
    }

    @AfterEach
    void tearDown() {
        ufValidator = null;
        context = null;
    }

    @Test
    void initialize() {
        assertDoesNotThrow(() -> ufValidator.initialize(null));
    }

    @Test
    void isValid_validUF_shouldReturnTrue() {
        String validUF = "SP";

        boolean isValid = ufValidator.isValid(validUF, context);

        assertTrue(isValid);
    }

    //Deve lançar illegal argument exception
    @Test
    void isValid_invalidUF_shouldReturnFalse() {
        String invalidUF = "XX";

        assertThrows(IllegalArgumentException.class, () -> ufValidator.isValid(invalidUF, context));
    }
}