package com.postech.fastfood.infrastructure.controller;

import com.postech.fastfood.application.gateways.LoggerPort;
import com.postech.fastfood.application.usecases.security.AuthorizeUserServiceAdapter;
import com.postech.fastfood.application.usecases.security.TokenServiceAdapter;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.controller.dto.request.AuthCustomerRequest;
import com.postech.fastfood.infrastructure.controller.dto.request.AuthEmployeeRequest;
import com.postech.fastfood.infrastructure.controller.dto.response.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthorizeUserServiceAdapter authorizeUser;
    private final TokenServiceAdapter tokenService;
    private final PasswordEncoder passwordEncoder;
    private final LoggerPort logger;

    @PostMapping("/employee")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthEmployeeRequest authEmployeeRequest) {
        final var user = authorizeUser.loadUserByUsername(authEmployeeRequest.email());
        if (!passwordEncoder.matches(authEmployeeRequest.password(), user.getPassword())) {
            logger.warn("[Auth] Falha na autenticação de funcionário: email={}", authEmployeeRequest.email());
            throw new FastFoodException("Senha inválida", "Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        final var token = tokenService.generateToken(user);
        final var authResponse = new AuthResponse(token);
        logger.info("[Auth] Funcionário autenticado com sucesso: {}", user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PostMapping("/customer")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthCustomerRequest authCustomerRequest) {
        final var user = authorizeUser.loadCustomerByCpf(authCustomerRequest.cpf());
        final var token = tokenService.generateToken(user);
        final var authResponse = new AuthResponse(token);
        logger.info("[Auth] Cliente autenticado com sucesso: {}", user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }
}


