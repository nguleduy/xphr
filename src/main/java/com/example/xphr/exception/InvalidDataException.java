package com.example.xphr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Invalid Data Exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
