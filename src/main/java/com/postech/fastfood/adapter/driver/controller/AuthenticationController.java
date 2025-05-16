package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.AuthRequest;
import com.postech.fastfood.adapter.driver.controller.dto.response.AuthResponse;
import com.postech.fastfood.application.AutorizaUsuario;
import com.postech.fastfood.application.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AutorizaUsuario autorizaUsuario;
    private final TokenService tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        final var user = autorizaUsuario.loadUserByUsername(authRequest.cpf());
        final var token = tokenService.generateToken(user);
        return new AuthResponse(token);
    }
}


