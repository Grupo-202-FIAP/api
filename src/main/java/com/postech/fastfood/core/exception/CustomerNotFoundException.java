package com.postech.fastfood.core.exception;

import java.io.Serial;

public class CustomerNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2241209891927997628L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
