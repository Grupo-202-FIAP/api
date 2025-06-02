package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.customer.FindCustomerByIdUseCase;
import java.util.UUID;

public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindCustomerByIdUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(UUID id) {
        return this.userRepositoryPort.findById(id);
    }
}
