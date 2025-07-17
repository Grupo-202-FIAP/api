package com.postech.fastfood.application.usecases.customer;

import com.postech.fastfood.application.gateways.UserRepository;
import com.postech.fastfood.application.usecases.FormatCpf;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.gateways.customer.CreateCustomerWithCpfUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class CreateCustomerWithCpfUseCaseImpl implements CreateCustomerWithCpfUseCase {
    private final UserRepository userRepository;

    public CreateCustomerWithCpfUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(User user) {
        final User userSaved;
        try {
            user.setCpf(FormatCpf.formatCpfToEntity(user.getCpf()));
            user.setRole(UserRole.ROLE_CUSTOMER);
            userSaved = this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new FastFoodException(e.getMessage(), "CPF already in use", HttpStatus.CONFLICT);
        }
        return userSaved;
    }

}
