package com.generation.backend.annotation;

import com.generation.backend.annotation.validator.PhoneValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para validar a classe PhoneValidator.
 */
@SpringBootTest
class PhoneValidatorTest {

    private PhoneValidator phoneValidator;
    private ConstraintValidatorContext context;

    /**
     * Método executado antes de cada teste. Inicializa o PhoneValidator e o contexto de validação.
     */
    @BeforeEach
    void setUp() {
        phoneValidator = new PhoneValidator();
        context = null; // Você pode configurar um contexto de validação personalizado se necessário
    }

    /**
     * Método executado após cada teste. Limpa as instâncias de PhoneValidator e do contexto de validação.
     */
    @AfterEach
    void tearDown() {
        phoneValidator = null;
        context = null;
    }

    /**
     * Testa o método initialize da classe PhoneValidator.
     * O método initialize não faz nada, então apenas verifica se não ocorre uma exceção.
     */
    @Test
    void initialize() {
        assertDoesNotThrow(() -> phoneValidator.initialize(null));
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone válido.
     * Deve retornar verdadeiro, indicando que o número de telefone é válido.
     */
    @Test
    void isValid_validPhoneNumber_shouldReturnTrue() {
        String validPhoneNumber = "(11) 12345-6789";

        boolean isValid = phoneValidator.isValid(validPhoneNumber, context);

        assertTrue(isValid);
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone inválido.
     * Deve lançar uma exceção IllegalArgumentException, indicando que o número de telefone é inválido.
     */
    @Test
    void isValid_invalidPhoneNumber_shouldThrowException() {
        String invalidPhoneNumber = "12345";

        assertThrows(IllegalArgumentException.class, () -> phoneValidator.isValid(invalidPhoneNumber, context));
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone nulo.
     * Deve retornar verdadeiro, indicando que o número de telefone é válido (nulo é considerado válido).
     */
    @Test
    void isValid_nullPhoneNumber_shouldReturnTrue() {
        String nullPhoneNumber = null;

        boolean isValid = phoneValidator.isValid(null, context);

        assertTrue(isValid);
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone vazio.
     * Deve retornar verdadeiro, indicando que o número de telefone é válido (uma string vazia é considerada válida).
     */
    @Test
    void isValid_emptyPhoneNumber_shouldReturnTrue() {
        String emptyPhoneNumber = "";

        boolean isValid = phoneValidator.isValid(emptyPhoneNumber, context);

        assertTrue(isValid);
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone com espaços.
     * Deve retornar verdadeiro, indicando que o número de telefone é válido.
     */
    @Test
    void isValid_phoneNumberWithSpaces_shouldReturnTrue() {
        String phoneNumberWithSpaces = "(11) 1234 5678";

        boolean isValid = phoneValidator.isValid(phoneNumberWithSpaces, context);

        assertTrue(isValid);
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone sem DDD.
     * Deve retornar verdadeiro, indicando que o número de telefone é válido.
     */
    @Test
    void isValid_phoneNumberWithoutDDD_shouldReturnTrue() {
        String phoneNumberWithoutDDD = "1234-5678";

        boolean isValid = phoneValidator.isValid(phoneNumberWithoutDDD, context);

        assertTrue(isValid);
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone que inclui caracteres inválidos.
     * Deve lançar uma exceção IllegalArgumentException, indicando que o número de telefone é inválido.
     */
    @Test
    void isValid_phoneNumberWithInvalidCharacters_shouldThrowException() {
        String phoneNumberWithInvalidCharacters = "(11) 1234-567X";

        assertThrows(IllegalArgumentException.class, () -> phoneValidator.isValid(phoneNumberWithInvalidCharacters, context));
    }

    /**
     * Testa o método isValid da classe PhoneValidator com um número de telefone que excede o comprimento máximo permitido.
     * Deve lançar uma exceção IllegalArgumentException, indicando que o número de telefone é inválido.
     */
    @Test
    void isValid_phoneNumberWithExcessiveLength_shouldThrowException() {
        String phoneNumberWithExcessiveLength = "(11) 12345-67890";

        assertThrows(IllegalArgumentException.class, () -> phoneValidator.isValid(phoneNumberWithExcessiveLength, context));
    }
}