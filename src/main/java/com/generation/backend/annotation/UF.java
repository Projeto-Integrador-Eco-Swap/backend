package com.generation.backend.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Anotação para validar a unidade federativa do Brasil.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UFValidator.class)
@Documented
public @interface UF {
    /**
     * Mensagem de erro padrão caso a unidade federativa seja inválida.
     */
    String message() default "Unidade federativa inválida do Brasil";

    /**
     * Grupos de validação, se necessário.
     */
    Class<?>[] groups() default {};

    /**
     * Cargas personalizadas de validação, se necessário.
     */
    Class<? extends Payload>[] payload() default {};
}
