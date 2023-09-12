package com.generation.backend.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Anotação para validar números de telefone.
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface Phone {
    /**
     * Mensagem de erro padrão caso o número de telefone seja inválido.
     */
    String message() default "Número de telefone inválido";

    /**
     * Grupos de validação, se necessário.
     */
    Class<?>[] groups() default {};

    /**
     * Cargas personalizadas de validação, se necessário.
     */
    Class<? extends Payload>[] payload() default {};
}