package com.generation.backend.exception;
/**
 * Exceção para quando o nome de uma categoria de produtos é inválido.
 */
public class InvalidProductCategoryException extends Exception {
    public InvalidProductCategoryException(String msg) {
        super(msg);
    }
}
