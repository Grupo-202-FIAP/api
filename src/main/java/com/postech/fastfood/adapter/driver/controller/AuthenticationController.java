package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.AuthRequest;
import com.postech.fastfood.application.AutorizaUsuario;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
public class AuthenticationController {
    
    private final AutorizaUsuario autorizaUsuario;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void authenticate(@RequestBody AuthRequest authRequest) {
        autorizaUsuario.loadUserByUsername(authRequest.cpf());
    }
}


