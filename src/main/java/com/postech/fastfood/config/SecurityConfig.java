package com.postech.fastfood.config;

import com.postech.fastfood.adapter.driver.filter.SecurityFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private static final String PRODUCT_ENDPOINT = "/product";
    private static final String CUSTOMER_ENDPOINT = "/customer";
    private static final String EMPLOYEE_ENDPOINT = "/employee";
    private static final String ORDER_ENDPOINT = "/order";
    private static final String AUTH_ENDPOINT = "/auth";
    private static final String SWAGGER_UI = "/swagger-ui";
    private static final String SWAGGER_API_DOCS = "/v3/api-docs";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_MANAGER = "MANAGER";
    private static final String ALL_URIS = "/**";
    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, PRODUCT_ENDPOINT + ALL_URIS).hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
                        .requestMatchers(HttpMethod.DELETE, PRODUCT_ENDPOINT + ALL_URIS).hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
                        .requestMatchers(HttpMethod.PUT, PRODUCT_ENDPOINT + ALL_URIS).hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
                        .requestMatchers(CUSTOMER_ENDPOINT + ALL_URIS).permitAll()
                        .requestMatchers(EMPLOYEE_ENDPOINT + ALL_URIS).permitAll()
                        .requestMatchers(AUTH_ENDPOINT + ALL_URIS).permitAll()
                        .requestMatchers(ORDER_ENDPOINT + ALL_URIS).permitAll()
                        .requestMatchers(SWAGGER_UI + ALL_URIS).permitAll()
                        .requestMatchers(SWAGGER_API_DOCS + ALL_URIS).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


