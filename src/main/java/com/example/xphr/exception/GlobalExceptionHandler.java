package com.example.xphr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception Handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Resource Not Found Exception Handler.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    /**
     * Bad Request Exception Handler.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * Generic Exception Handler.
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleGenericException(Exception ex) {
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    /**
     * Handle exception when the data is conflicted.
     *
     * @param e InvalidDataException
     * @return ErrorResponse
     */
    @ExceptionHandler(InvalidDataException.class)
    public ApiResponse<Object> handleDuplicateKeyException(InvalidDataException e) {
        return ApiResponse.error(HttpStatus.CONFLICT.value(), e.getMessage());
    }
}
