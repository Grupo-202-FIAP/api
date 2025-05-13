package com.postech.fastfood.core.exception;

import java.io.Serial;

public class CpfAlreadyInUseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2746773781443989795L;

    public CpfAlreadyInUseException(String message) {
        super(message);
    }
}
