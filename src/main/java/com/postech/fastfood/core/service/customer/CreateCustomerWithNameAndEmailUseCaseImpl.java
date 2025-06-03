package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithNameAndEmailUseCase;
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
