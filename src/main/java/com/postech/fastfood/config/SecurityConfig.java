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
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_MANAGER = "MANAGER";
//    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, PRODUCT_ENDPOINT + "/**").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
                        .requestMatchers(HttpMethod.DELETE, PRODUCT_ENDPOINT + "/**").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
                        .requestMatchers(HttpMethod.PUT, PRODUCT_ENDPOINT + "/**").hasAnyRole(ROLE_ADMIN, ROLE_MANAGER)
                        .requestMatchers(CUSTOMER_ENDPOINT + "/**").permitAll()
                        .requestMatchers(EMPLOYEE_ENDPOINT + "/**").permitAll()
                        .requestMatchers(AUTH_ENDPOINT + "/**").permitAll()
                        .requestMatchers(ORDER_ENDPOINT + "/**").permitAll()
                        .anyRequest().authenticated()
                )
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
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


