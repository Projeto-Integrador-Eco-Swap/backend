package com.generation.backend.exception;

/**
 * Exceção para quando o id de um endereço é inválido.
 */
public class InvalidAddressIdException extends Exception {
    public InvalidAddressIdException(String msg) {
        super(msg);
    }
}
