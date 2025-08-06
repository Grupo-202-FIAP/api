package com.postech.fastfood.core.service.employee;

import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.PasswordEncoderPort;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.usecase.employee.CreateEmployeeUseCase;
import com.postech.fastfood.core.utils.FormatCpf;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

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
            user.setCpf(FormatCpf.formatCpfToEntity(user.getCpf()));
            user.setPassword(passwordEncoderPort.encode(user.getPassword()));
            userSaved = (Employee) this.userRepositoryPort.save(user);
        } catch (DataIntegrityViolationException e) {
            final String message = e.getMostSpecificCause().getMessage();
            if (message != null) {
                if (message.contains("email_unique_constraint") || message.toLowerCase().contains("email")) {
                    throw new FastFoodException(
                            "Email already exists",
                            "Email already exists",
                            HttpStatus.CONFLICT
                    );

                } else if (message.contains("cpf_unique_constraint") || message.toLowerCase().contains("cpf")) {
                    throw new FastFoodException(
                            "CPF already exists",
                            "CPF already exists",
                            HttpStatus.CONFLICT
                    );

                }
            }
        }
        return userSaved;
    }
}
