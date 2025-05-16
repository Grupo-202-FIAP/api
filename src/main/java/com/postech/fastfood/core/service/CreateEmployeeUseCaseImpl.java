package com.postech.fastfood.core.service;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.exception.CpfAlreadyInUseException;
import com.postech.fastfood.core.exception.EmailAlreadyExistsException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.CreateEmployeeUseCase;
import org.springframework.dao.DataIntegrityViolationException;

public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateEmployeeUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(User user) {
        User userSaved= null;
        try {
            user.setCpf(user.getCpf().replace(".",""));
            user.setCpf(user.getCpf().replace("-",""));
            userSaved = this.userRepositoryPort.save(user);
        } catch (DataIntegrityViolationException e) {
            String message = e.getMostSpecificCause().getMessage();
            if (message != null) {
                if (message.contains("email_unique_constraint") || message.toLowerCase().contains("email")) {
                    throw new EmailAlreadyExistsException("Email já cadastrado");
                } else if (message.contains("cpf_unique_constraint") || message.toLowerCase().contains("cpf")) {
                    throw new CpfAlreadyInUseException("CPF já cadastrado");
                }
            }
        }
        return userSaved;
    }
}
