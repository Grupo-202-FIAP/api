package com.postech.fastfood.core.service;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.FindUserByEmailUseCase;

public class FindUserByEmailUseCaseImpl implements FindUserByEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserByEmailUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(String email) {
        return this.userRepositoryPort.findByEmail(email);
    }
}
