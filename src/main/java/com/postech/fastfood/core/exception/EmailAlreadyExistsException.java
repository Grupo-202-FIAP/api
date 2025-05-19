package com.postech.fastfood.core.exception;

import java.io.Serial;

public class EmailAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2208531374911356929L;

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
