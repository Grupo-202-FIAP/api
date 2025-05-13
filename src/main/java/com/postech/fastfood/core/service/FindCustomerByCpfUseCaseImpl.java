package com.postech.fastfood.core.service;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.FindCustomerByCpfUseCase;

public class FindCustomerByCpfUseCaseImpl implements FindCustomerByCpfUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindCustomerByCpfUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(String cpf) {
        return this.userRepositoryPort.findCustomerByCpf(cpf);
    }

}
