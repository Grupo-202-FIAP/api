package com.postech.fastfood.adapter.driver.filter;

import com.postech.fastfood.adapter.driven.persistence.repository.IEmployeeEntityRepository;
import com.postech.fastfood.adapter.driven.security.TokenServiceAdapter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenServiceAdapter tokenService;
    private final IEmployeeEntityRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final var token = recoverToken(request);
        if (token != null) {
            final var validateToken = tokenService.validateToken(token);
            final var user = userRepository.findByEmail(validateToken);
            if (user.isPresent()) {
                final var auth = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        final var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
