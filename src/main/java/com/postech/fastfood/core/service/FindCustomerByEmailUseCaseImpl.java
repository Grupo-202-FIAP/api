package com.postech.fastfood.core.service;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.FindCustomerByCpfUseCase;
import com.postech.fastfood.core.usecase.FindCustomerByEmailUseCase;

public class FindCustomerByEmailUseCaseImpl implements FindCustomerByEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindCustomerByEmailUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(String email) {
        return this.userRepositoryPort.findCustomerByEmail(email);
    }
}
