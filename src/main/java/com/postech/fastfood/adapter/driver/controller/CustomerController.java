package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.application.mapper.UserMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.usecase.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.CreateCustomerWithNameAndEmailUseCase;
import com.postech.fastfood.core.usecase.FindUserByCpfUseCase;
import com.postech.fastfood.core.usecase.FindUserByEmailUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase;
    private final CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase;
    private final FindUserByEmailUseCase findUserByEmail;
    private final FindUserByCpfUseCase findUserByCpfUseCase;

    public CustomerController(CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase, CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase, FindUserByEmailUseCase findCustomerByEmail, FindUserByCpfUseCase findUserByCpfUseCase) {
        this.createCustomerWithCpfUseCase = createCustomerWithCpfUseCase;
        this.createCustomerWithNameAndEmailUseCase = createCustomerWithNameAndEmailUseCase;
        this.findUserByEmail = findCustomerByEmail;
        this.findUserByCpfUseCase = findUserByCpfUseCase;
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
        User customerSaved = this.findUserByEmail.execute(email);
        return ResponseEntity.status(200).body(customerSaved);
    }

    @GetMapping("/cpf")
    public ResponseEntity<User> getCustomerByCpf(@RequestParam String cpf ){
        User customerSaved = this.findUserByCpfUseCase.execute(cpf);
        return ResponseEntity.status(200).body(customerSaved);
    }



}
