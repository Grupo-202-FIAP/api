package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.application.mapper.UserMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.service.FindCustomerByEmailUseCaseImpl;
import com.postech.fastfood.core.usecase.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.CreateCustomerWithNameAndEmailUseCase;
import com.postech.fastfood.core.usecase.FindCustomerByCpfUseCase;

import com.postech.fastfood.core.usecase.FindCustomerByEmailUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase;
    private final CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase;
    private final FindCustomerByEmailUseCase findCustomerByEmail;
    private final FindCustomerByCpfUseCase findCustomerByCpfUseCase;

    public CustomerController(CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase, CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase, FindCustomerByEmailUseCase findCustomerByEmail, FindCustomerByCpfUseCase findCustomerByCpfUseCase1) {
        this.createCustomerWithCpfUseCase = createCustomerWithCpfUseCase;
        this.createCustomerWithNameAndEmailUseCase = createCustomerWithNameAndEmailUseCase;
        this.findCustomerByEmail = findCustomerByEmail;
        this.findCustomerByCpfUseCase = findCustomerByCpfUseCase1;
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

    @GetMapping("/email")
    public ResponseEntity<User> getCustomerByEmail(@RequestParam String email ){
        User customerSaved = this.findCustomerByEmail.execute(email);
        return ResponseEntity.status(200).body(customerSaved);
    }

    @GetMapping("/cpf")
    public ResponseEntity<User> getCustomerByCpf(@RequestParam String cpf ){
        User customerSaved = this.findCustomerByCpfUseCase.execute(cpf);
        return ResponseEntity.status(200).body(customerSaved);
    }



}
