package com.postech.fastfood.application.usecases.customer;

import com.postech.fastfood.application.gateways.UserRepository;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.infrastructure.gateways.customer.FindCustomerByIdUseCase;
import java.util.UUID;

public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private final UserRepository userRepository;

    public FindCustomerByIdUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(UUID id) {
        return this.userRepository.findById(id);
    }
}
