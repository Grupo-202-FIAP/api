package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.EmployeeRequest;
import com.postech.fastfood.application.mapper.UserMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.service.CreateEmployeeUseCaseImpl;
import com.postech.fastfood.core.usecase.CreateEmployeeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployeeUseCase;

    public EmployeeController(CreateEmployeeUseCase createEmployeeUseCase) {
        this.createEmployeeUseCase = createEmployeeUseCase;
    }


    @PostMapping()
    public ResponseEntity<User> createemployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        User employee = UserMapper.toDomain(employeeRequest);
        return ResponseEntity.status(201).body(this.createEmployeeUseCase.execute(employee));
    }

}
