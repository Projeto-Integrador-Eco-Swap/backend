package com.generation.backend.annotation;

import com.generation.backend.annotation.validator.CEPValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para validar o formato de CEP (Código de Endereçamento Postal).
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CEPValidator.class})
@Documented
public @interface CEP {
    /**
     * Mensagem de erro padrão caso o formato do CEP seja inválido.
     */
    String message() default "Formato de CEP inválido";

    /**
     * Grupos de validação, se necessário.
     */
    Class<?>[] groups() default {};

    /**
     * Cargas personalizadas de validação, se necessário.
     */
    Class<? extends Payload>[] payload() default {};
}