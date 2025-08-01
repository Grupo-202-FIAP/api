package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.EmployeeRequest;
import com.postech.fastfood.application.mapper.EmployeeMapper;
import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.usecase.employee.CreateEmployeeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final LoggerPort logger;

    public EmployeeController(
            CreateEmployeeUseCase createEmployeeUseCase,
            LoggerPort logger) {
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.logger = logger;
    }

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        logger.info("[Employee] Iniciando criação de funcionário");
        final Employee employee = EmployeeMapper.toDomain(employeeRequest);
        this.createEmployeeUseCase.execute(employee);
        logger.info("[Employee] Funcionário criado com sucesso: id={}", employee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
