package com.generation.backend.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.executable.ValidateOnExecution;

import java.util.Arrays;
import java.util.List;

/**
 * Custom validator for Brazilian Unidade Federativa (UF).
 */
@ValidateOnExecution
public class UFValidator implements ConstraintValidator<UF, String> {

    /**
     * Valid UF (Unidade Federativa) values as constants
     */
    private static final List<String> VALID_UFS = Arrays.asList(
            "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB",
            "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    );

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The UF annotation (not used in this case).
     */
    @Override
    public void initialize(UF constraintAnnotation) {
    }

    /**
     * Validates if the provided UF is valid.
     *
     * @param value   The UF to be validated.
     * @param context The validation context (not used in this case).
     * @return True if the UF is valid, false otherwise.
     * @throws IllegalArgumentException If an invalid UF is provided.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        String upperCaseValue = value.toUpperCase();
        if (!VALID_UFS.contains(upperCaseValue)) {
            throw new IllegalArgumentException("Invalid UF: " + value);
        }

        return true;
    }
}
