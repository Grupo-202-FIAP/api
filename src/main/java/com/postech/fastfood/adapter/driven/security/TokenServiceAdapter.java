package com.postech.fastfood.adapter.driven.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.postech.fastfood.adapter.driven.persistence.entity.UserEntity;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.LoggerPort;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceAdapter {

    public static final int HOURS = 2;
    private final LoggerPort logger;
    @Value("${api.security.token.secret}")
    private String secret;

    public TokenServiceAdapter(LoggerPort logger) {
        this.logger = logger;
    }

    public String generateToken(UserEntity user) {
        logger.info("[Token] Gerando token para usuário: id={}, role={}", user.getId(), user.getRole());
        try {
            final var algorithm = Algorithm.HMAC256(secret);

            final String token = JWT.create()
                    .withIssuer("FastFood")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId().toString())
                    .withClaim("name", user.getName())
                    .withClaim("cpf", user.getCpf())
                    .withClaim("role", user.getRole().getRole())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            logger.info("[Token] Token gerado com sucesso para usuário: id={}", user.getId());

            return token;

        } catch (JWTCreationException e) {
            logger.error("[Token] Erro ao gerar token para usuário id={}: {}", user.getId(), e.getMessage());
            throw new FastFoodException(e.getLocalizedMessage(), "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String validateToken(String token) {
        logger.debug("[Token] Validando token recebido");

        try {
            final var algorithm = Algorithm.HMAC256(secret);
            final String subject = JWT.require(algorithm).withIssuer("FastFood").build().verify(token).getSubject();
            logger.info("[Token] Token válido para subject: {}", subject);
            return subject;
        } catch (JWTVerificationException e) {
            logger.warn("[Token] Token inválido ou expirado");
            throw new FastFoodException("Invalid or expired token", "Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(HOURS).toInstant(ZoneOffset.of("-03:00"));
    }
}
