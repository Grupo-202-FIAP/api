package com.postech.fastfood.core.ports;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}
