package com.postech.fastfood.core.exception;

public class FastFoodException extends RuntimeException {
    public final String title;
    public final int status;

    public FastFoodException(String message, String title, int status) {
        super(message);
        this.title = title;
        this.status = status;
    }
}
