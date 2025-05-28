package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.FindUserByCpfUseCase;

public class FindCustomerByCpfUseCaseImpl implements FindUserByCpfUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindCustomerByCpfUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(String cpf) {
        return this.userRepositoryPort.findByCpf(cpf);
    }

}
