package com.postech.fastfood.application.usecases.customer;

import com.postech.fastfood.application.gateways.UserRepository;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;
import com.postech.fastfood.infrastructure.gateways.FindUserByEmailUseCase;

public class FindCustomerByEmailUseCaseImpl implements FindUserByEmailUseCase {

    private final UserRepository userRepository;

    public FindCustomerByEmailUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(String email, UserRole userRole) {
        return this.userRepository.findByEmail(email, userRole);
    }
}
