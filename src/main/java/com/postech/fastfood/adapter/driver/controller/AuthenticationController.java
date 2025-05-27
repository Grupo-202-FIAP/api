package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driven.persistence.entity.UserEntity;
import com.postech.fastfood.adapter.driven.security.AuthorizeUserServiceAdapter;
import com.postech.fastfood.adapter.driven.security.TokenServiceAdapter;
import com.postech.fastfood.adapter.driver.controller.dto.request.AuthRequest;
import com.postech.fastfood.adapter.driver.controller.dto.response.AuthResponse;
import com.postech.fastfood.core.exception.FastFoodException;
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

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        final UserEntity user = authorizeUser.loadUserByUsername(authRequest.email());
        if (!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            throw new FastFoodException("Senha inválida", "Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        final String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}


