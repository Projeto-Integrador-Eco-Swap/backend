package com.generation.backend.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class InvalidProductCategoryExceptionTest {

    /**
     * Testa a exceção InvalidIdProductCategoryException lançada com uma mensagem personalizada.
     */
    @Test
    void testInvalidProductCategoryExceptionWithCustomMessage() {
        // Define a mensagem de erro esperada
        String expectedErrorMessage = "Teste de exceção personalizada";

        // Lança a exceção e verifica se a mensagem é igual à esperada
        InvalidIdProductCategoryException exception = assertThrows(
                InvalidIdProductCategoryException.class,
                () -> {
                    throw new InvalidIdProductCategoryException(expectedErrorMessage);
                }
        );
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    /**
     * Testa a exceção InvalidIdProductCategoryException lançada com mensagem padrão.
     */
    @Test
    void testInvalidProductCategoryExceptionWithDefaultMessage() {
        // Define a mensagem de erro esperada
        String expectedErrorMessage = "Invalid product category ID";
        
        // Lança a exceção sem especificar uma mensagem personalizada
        InvalidIdProductCategoryException exception = assertThrows(
                InvalidIdProductCategoryException.class,
                () -> {
                    throw new InvalidIdProductCategoryException(expectedErrorMessage);
                }
        );

        // Verifica se a mensagem padrão é igual à mensagem esperada
        assertEquals("Invalid product category ID", exception.getMessage());
    }
}
