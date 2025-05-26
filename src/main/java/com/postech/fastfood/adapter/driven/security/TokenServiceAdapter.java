package com.postech.fastfood.adapter.driven.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.postech.fastfood.adapter.driven.persistence.entity.UserEntity;
import com.postech.fastfood.core.exception.FastFoodException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceAdapter {

    public static final int HOURS = 2;
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            final var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("FastFood")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId().toString())
                    .withClaim("name", user.getName())
                    .withClaim("cpf", user.getCpf())
                    .withClaim("role", user.getRole().getRole())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new FastFoodException(e.getLocalizedMessage(), "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String validateToken(String token) {
        try {
            final var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("FastFood").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            throw new FastFoodException("Invalid or expired token", "Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(HOURS).toInstant(ZoneOffset.of("-03:00"));
    }
}
