package com.generation.backend.exception;

/**
 * Exceção para quando o id de um endereço é inválido.
 */
public class EmailUserNotFoundException extends Exception {
        public EmailUserNotFoundException(String email) {
            super("User with email " + email + " not found.");
        }
}
