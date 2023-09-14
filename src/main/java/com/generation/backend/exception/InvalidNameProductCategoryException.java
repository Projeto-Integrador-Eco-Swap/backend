package com.generation.backend.exception;

/**
 * Exceção para quando o nome de uma categoria de produtos é inválido.
 */
public class InvalidNameProductCategoryException extends Exception {
    public InvalidNameProductCategoryException(String s) {
        super(s);
    }
}
