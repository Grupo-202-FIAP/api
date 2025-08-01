package com.postech.fastfood.application.usecases.customer;

import com.postech.fastfood.application.gateways.UserRepositoryPort;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.infrastructure.gateways.customer.FindCustomerByIdUseCase;
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
