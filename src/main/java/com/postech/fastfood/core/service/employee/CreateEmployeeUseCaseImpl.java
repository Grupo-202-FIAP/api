package com.postech.fastfood.core.service.employee;

import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.exception.CpfAlreadyInUseException;
import com.postech.fastfood.core.exception.EmailAlreadyExistsException;
import com.postech.fastfood.core.ports.PasswordEncoderPort;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.employee.CreateEmployeeUseCase;
import org.springframework.dao.DataIntegrityViolationException;

public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public CreateEmployeeUseCaseImpl(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public Employee execute(Employee user) {
        Employee userSaved = null;
        try {
            user.setCpf(user.getCpf().replace(".", ""));
            user.setCpf(user.getCpf().replace("-", ""));
            user.setPassword(passwordEncoderPort.encode(user.getPassword()));
            userSaved = (Employee) this.userRepositoryPort.save(user);
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
