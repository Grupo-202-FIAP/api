package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.exception.EmailAlreadyExistsException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.CreateCustomerWithNameAndEmailUseCase;
import org.springframework.dao.DataIntegrityViolationException;

public class CreateCustomerWithNameAndEmailUseCaseImpl implements CreateCustomerWithNameAndEmailUseCase
{
    private UserRepositoryPort userRepositoryPort;

    public CreateCustomerWithNameAndEmailUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(User user) {
        User userSaved;
        try {
                userSaved = this.userRepositoryPort.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyExistsException(e.getMessage());
        }
        return userSaved;
    }

}
