package com.postech.fastfood.application.gateways;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}
