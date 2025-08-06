package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.FindUserByEmailUseCase;

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
