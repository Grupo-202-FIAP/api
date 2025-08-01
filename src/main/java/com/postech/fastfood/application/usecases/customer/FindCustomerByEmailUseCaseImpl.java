package com.postech.fastfood.application.usecases.customer;

import com.postech.fastfood.application.gateways.UserRepositoryPort;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;
import com.postech.fastfood.infrastructure.gateways.FindUserByEmailUseCase;

public class FindCustomerByEmailUseCaseImpl implements FindUserByEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindCustomerByEmailUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(String email, UserRole userRole) {
        return this.userRepositoryPort.findByEmail(email, userRole);
    }
}
