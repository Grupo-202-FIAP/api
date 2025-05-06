package com.postech.fastfood.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final URI URI_VAZIA = URI.create("");

    private ProblemDetail montarProblemDetail(String title, HttpStatus status, String message) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setType(URI_VAZIA);
        problemDetail.setTitle(title);
        return problemDetail;
    }

    @ExceptionHandler(FastFoodException.class)
    public ProblemDetail fastFoodException(FastFoodException e) {
        return montarProblemDetail(e.title, HttpStatus.valueOf(e.status), e.getLocalizedMessage());
    }
}
