package com.example.xphr.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Api Response.
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int statusCode;
    private T data;
    private String errorMessage;

    /**
     * Success Response.
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, data, null);
    }

    /**
     * Error Response.
     */
    public static <T> ApiResponse<T> error(int statusCode, String errorMessage) {
        return new ApiResponse<>(statusCode, null, errorMessage);
    }
}
