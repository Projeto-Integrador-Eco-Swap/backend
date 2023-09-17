package com.generation.backend.annotation;

import com.generation.backend.annotation.validator.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para validar números de telefone.
 */
@Target({ElementType.FIELD})
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