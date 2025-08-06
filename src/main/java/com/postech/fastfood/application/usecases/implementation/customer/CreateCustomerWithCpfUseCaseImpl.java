package com.postech.fastfood.application.usecases.implementation.customer;

import com.postech.fastfood.application.gateways.UserRepositoryPort;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.application.usecases.interfaces.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.utils.FormatCpf;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class CreateCustomerWithCpfUseCaseImpl implements CreateCustomerWithCpfUseCase {
    private final UserRepositoryPort userRepositoryPort;

    public CreateCustomerWithCpfUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(User user) {
        final User userSaved;
        try {
            user.setCpf(FormatCpf.formatCpfToEntity(user.getCpf()));
            user.setRole(UserRole.ROLE_CUSTOMER);
            userSaved = this.userRepositoryPort.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new FastFoodException(e.getMessage(), "CPF already in use", HttpStatus.CONFLICT);
        }
        return userSaved;
    }

}
