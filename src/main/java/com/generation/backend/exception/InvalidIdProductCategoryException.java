package com.generation.backend.exception;

/**
 * Exceção para quando o nome de uma categoria de produtos é inválido.
 */
public class InvalidIdProductCategoryException extends Exception {
    public InvalidIdProductCategoryException(String msg) {
        super(msg);
    }
}
