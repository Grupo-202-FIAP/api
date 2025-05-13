package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.application.mapper.UserMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.usecase.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.CreateCustomerWithNameAndEmailUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase;
    private final CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase;

    public CustomerController(CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase, CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase) {
        this.createCustomerWithCpfUseCase = createCustomerWithCpfUseCase;
        this.createCustomerWithNameAndEmailUseCase = createCustomerWithNameAndEmailUseCase;
    }

    @PostMapping("/cpf")
    public ResponseEntity<User> createCustomerWithCpf(@RequestBody @Valid CustomerCpfRequest customerCpfRequest){
        User customerCpf = UserMapper.toDomain(customerCpfRequest);
        User customerSaved = this.createCustomerWithCpfUseCase.execute(customerCpf);
        return ResponseEntity.status(201).body(customerSaved);
    }

    @PostMapping("/email")
    public ResponseEntity<User> createCustomerWithEmailAndName(@RequestBody @Valid CustomerEmailRequest customerEmailRequest){
        User customer = UserMapper.toDomain(customerEmailRequest);
        User customerSaved = this.createCustomerWithNameAndEmailUseCase.execute(customer);
        return ResponseEntity.status(201).body(customerSaved);
    }


}
