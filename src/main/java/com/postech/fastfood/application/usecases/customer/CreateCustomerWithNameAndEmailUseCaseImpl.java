package com.postech.fastfood.application.usecases.customer;

import com.postech.fastfood.application.gateways.UserRepositoryPort;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.gateways.customer.CreateCustomerWithNameAndEmailUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class CreateCustomerWithNameAndEmailUseCaseImpl implements CreateCustomerWithNameAndEmailUseCase {
    private final UserRepositoryPort userRepositoryPort;

    public CreateCustomerWithNameAndEmailUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(User user) {
        final User userSaved;
        try {
            user.setRole(UserRole.ROLE_CUSTOMER);
            userSaved = this.userRepositoryPort.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new FastFoodException(
                    e.getMessage(),
                    "Email already in use",
                    HttpStatus.CONFLICT
            );

        }
        return userSaved;
    }

}
