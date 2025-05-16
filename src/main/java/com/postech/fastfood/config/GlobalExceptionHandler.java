package com.postech.fastfood.config;

import com.postech.fastfood.core.exception.CpfAlreadyInUseException;
import com.postech.fastfood.core.exception.CustomerNotFoundException;
import com.postech.fastfood.core.exception.EmailAlreadyExistsException;
import com.postech.fastfood.core.exception.FastFoodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
        return montarProblemDetail(e.title, e.status, e.getLocalizedMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail authenticationException(AuthenticationException e) {
        return montarProblemDetail("Teste", HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CpfAlreadyInUseException.class)
    public ResponseEntity<String> handleCpfAlreadyExists(CpfAlreadyInUseException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This resource already exists");
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This resource already exists");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
