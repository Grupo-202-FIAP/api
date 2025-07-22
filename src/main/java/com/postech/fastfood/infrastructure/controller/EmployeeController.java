package com.postech.fastfood.infrastructure.controller;

import com.postech.fastfood.application.mapper.EmployeeMapper;
import com.postech.fastfood.domain.Employee;
import com.postech.fastfood.infrastructure.controller.dto.request.EmployeeRequest;
import com.postech.fastfood.infrastructure.gateways.employee.CreateEmployeeUseCase;
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

    public EmployeeController(CreateEmployeeUseCase createEmployeeUseCase) {
        this.createEmployeeUseCase = createEmployeeUseCase;
    }

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        final Employee employee = EmployeeMapper.toDomain(employeeRequest);
        this.createEmployeeUseCase.execute(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
