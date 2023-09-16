package com.generation.backend.annotation.validator;


import com.generation.backend.annotation.CEP;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.executable.ValidateOnExecution;

import java.util.regex.Pattern;

/**
 * Validates CEP (Código de Endereçamento Postal) format.
 */
@ValidateOnExecution
public class CEPValidator implements ConstraintValidator<CEP, String> {

    /**
     * Regular expression to validate CEP formats.
     */
    private static final String CEP_REGEX = "\\d{5}-?\\d{3}|\\d{2}\\.\\d{3}-\\d{2}|\\d{8}";
    /**
     * Compiled pattern to validate CEP formats.
     */
    private static final Pattern CEP_PATTERN = Pattern.compile(CEP_REGEX);

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The CEP annotation (not used in this case).
     */
    @Override
    public void initialize(CEP constraintAnnotation) {
    }

    /**
     * Validates if the provided CEP format is valid.
     *
     * @param value   The CEP to be validated.
     * @param context The validation context (not used in this case).
     * @return True if the CEP is valid, false otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        return CEP_PATTERN.matcher(value).matches();
    }
}