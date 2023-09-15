package com.generation.backend.handler;

import com.generation.backend.exception.InvalidIdProductCategoryException;
import com.generation.backend.exception.InvalidNameProductCategoryException;
import com.generation.backend.exception.InvalidProductCategoryException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manipulador global de exceções para controladores REST na aplicação.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    /**
     * Manipula várias exceções, incluindo exceções personalizadas e exceções genéricas.
     *
     * @param ex A exceção a ser manipulada.
     * @return Um ResponseEntity contendo um objeto ApiError com informações sobre a exceção.
     */
    @ExceptionHandler({
            InvalidIdProductCategoryException.class,
            InvalidNameProductCategoryException.class,
            InvalidProductCategoryException.class,
            Exception.class
    })
    protected ResponseEntity<Object> handleException(@NotNull Exception ex) {
        ApiError apiError = buildApiError(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Constrói um ResponseEntity contendo o objeto ApiError e o código de status HTTP.
     *
     * @param apiError O objeto ApiError a ser incluído na resposta.
     * @return Um ResponseEntity com a resposta de erro.
     */
    private @NotNull ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * Constrói um objeto ApiError com o código de status HTTP e a mensagem fornecidos.
     *
     * @param message A mensagem de erro.
     * @return Um objeto ApiError construído com os parâmetros fornecidos.
     */
    private @NotNull ApiError buildApiError(String message) {
        return new ApiError(HttpStatus.BAD_REQUEST, message);
    }
}