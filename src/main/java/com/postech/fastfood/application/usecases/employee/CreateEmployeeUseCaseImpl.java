package com.postech.fastfood.application.usecases.employee;

import com.postech.fastfood.application.gateways.PasswordEncoderPort;
import com.postech.fastfood.application.gateways.UserRepositoryPort;
import com.postech.fastfood.domain.Employee;
import com.postech.fastfood.domain.exception.FastFoodException;
import com.postech.fastfood.infrastructure.gateways.employee.CreateEmployeeUseCase;
import com.postech.fastfood.utils.FormatCpf;
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
                    throw new FastFoodException("Email already exists", "Email already exists", HttpStatus.CONFLICT);
                } else if (message.contains("cpf_unique_constraint") || message.toLowerCase().contains("cpf")) {
                    throw new FastFoodException("CPF already exists", "CPF already exists", HttpStatus.CONFLICT);

                }
            }
        }
        return userSaved;
    }
}
