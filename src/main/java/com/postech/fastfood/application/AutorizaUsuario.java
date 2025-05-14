package com.postech.fastfood.application;

import com.postech.fastfood.application.repository.UserRepositoryPortOut;
import com.postech.fastfood.core.exception.FastFoodException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutorizaUsuario {

    private final UserRepositoryPortOut userRepository;

    public UserDetails autorizarUsuarioPor(String cpf) {
        final var user = userRepository.findByCpf(cpf);
        if (user.isEmpty()) {
            throw new FastFoodException("User not found", "Not Found", HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

}
