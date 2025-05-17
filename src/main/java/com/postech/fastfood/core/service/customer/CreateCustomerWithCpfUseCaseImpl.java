package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.exception.CpfAlreadyInUseException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.CreateCustomerWithCpfUseCase;
import org.springframework.dao.DataIntegrityViolationException;

public class CreateCustomerWithCpfUseCaseImpl implements CreateCustomerWithCpfUseCase
{
    private UserRepositoryPort userRepositoryPort;

    public CreateCustomerWithCpfUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(User user) {
        User userSaved;
        try {
                user.setCpf(user.getCpf().replace(".",""));
                user.setCpf(user.getCpf().replace("-",""));
                userSaved = this.userRepositoryPort.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new CpfAlreadyInUseException(e.getMessage());
        }
       return userSaved ;
    }

}
