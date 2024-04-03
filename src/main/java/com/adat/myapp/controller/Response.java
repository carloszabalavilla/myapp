package com.adat.myapp.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Response.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {
    /**
     * The constant NO_ERROR.
     */
    public static final int NO_ERROR = 0;
    /**
     * The constant NOT_FOUND.
     */
    public static final int NOT_FOUND = 101;
    /**
     * The constant NO_MESSAGE.
     */
    public static final String NO_MESSAGE = "";
    private Error error;

    /**
     * No error response response.
     *
     * @return the response
     */
    public static Response noErrorResponse() {
        return new Response(new Error(NO_ERROR, NO_MESSAGE));
    }

    /**
     * Error resonse response.
     *
     * @param errorCode    the error code
     * @param errorMessage the error message
     * @return the response
     */
    public static Response errorResonse(int errorCode, String errorMessage) {
        return new Response(new Error(errorCode, errorMessage));
    }

    /**
     * The type Error.
     */
    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Error {
        private long errorCode;
        private String message;
    }
}