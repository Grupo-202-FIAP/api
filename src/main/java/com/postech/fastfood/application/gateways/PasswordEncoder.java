package com.postech.fastfood.application.gateways;

public interface PasswordEncoder {
    String encode(String rawPassword);
}
