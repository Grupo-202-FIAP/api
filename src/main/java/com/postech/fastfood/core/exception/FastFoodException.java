package com.postech.fastfood.core.exception;

import org.springframework.http.HttpStatus;

public class FastFoodException extends RuntimeException {
    public final String title;
    public final HttpStatus status;

    public FastFoodException(String message, String title, HttpStatus status) {
        super(message);
        this.title = title;
        this.status = status;
    }
}
