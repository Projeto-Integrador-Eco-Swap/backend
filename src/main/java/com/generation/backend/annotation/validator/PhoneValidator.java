package com.generation.backend.annotation.validator;

import com.generation.backend.annotation.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.executable.ValidateOnExecution;

import java.util.regex.Pattern;

/**
 * Validador personalizado para números de telefone brasileiros.
 */
@ValidateOnExecution
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    /**
     * Expressões regulares para vários padrões de números de telefone.
     */
    private static final String PADRAO_GERAL = "\\(?(\\d{2})\\)?[-. ]?(\\d{4,5})[-. ]?(\\d{4})";
    private static final String PADRAO_GERAL_SEM_DDD = "\\d{4,5}[-. ]?\\d{4}";
    private static final String PADRAO_GERAL_COM_DDD = "\\(?(\\d{2})\\)?[-. ]?(\\d{4,5})[-. ]?(\\d{4})";
    private static final String PADRAO_DDD_9_DIGITOS = "\\(?(\\d{2})\\)?[-. ]?(9\\d{4}[-. ]?\\d{4})";
    private static final String PADRAO_DDD_9_DIGITOS_SEPARADOR_OPCIONAL = "\\(?(\\d{2})\\)?[-. ]?9?\\d{4}[-. ]?\\d{4}";
    private static final String PADRAO_INTERNACIONAL = "\\+55\\(?(\\d{2})\\)?[-. ]?(\\d{4,5})[-. ]?(\\d{4})";

    /**
     * Padrão de regex combinado para todos os formatos de números de telefone.
     */
    private static final String PHONE_REGEX = String.format("(%s|%s|%s|%s|%s|%s)",
            PADRAO_GERAL,
            PADRAO_GERAL_SEM_DDD,
            PADRAO_GERAL_COM_DDD,
            PADRAO_DDD_9_DIGITOS,
            PADRAO_DDD_9_DIGITOS_SEPARADOR_OPCIONAL,
            PADRAO_INTERNACIONAL
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX, Pattern.UNICODE_CHARACTER_CLASS);

    /**
     * Inicializa o validador.
     *
     * @param constraintAnnotation A anotação Phone (não usada neste caso).
     */
    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    /**
     * Valida se o número de telefone fornecido é válido.
     *
     * @param value   O número de telefone a ser validado.
     * @param context O contexto de validação (não usado neste caso).
     * @return True se o número de telefone for válido, caso contrário, false.
     * @throws IllegalArgumentException Se for fornecido um número de telefone inválido.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        if (!PHONE_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Número de telefone inválido: " + value);
        }

        return true;
    }
}
