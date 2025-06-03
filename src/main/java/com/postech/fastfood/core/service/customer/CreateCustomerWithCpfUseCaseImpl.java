package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.utils.FormatCpf;
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
            throw new FastFoodException(
                    e.getMessage(),
                    "CPF already in use",
                    HttpStatus.CONFLICT
            );
        }
        return userSaved;
    }

}
