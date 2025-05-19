package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerByEmailRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.application.mapper.CustomerMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.usecase.FindUserByCpfUseCase;
import com.postech.fastfood.core.usecase.FindUserByEmailUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithNameAndEmailUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase;
    private final CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase;
    private final FindUserByEmailUseCase findUserByEmail;
    private final FindUserByCpfUseCase findUserByCpfUseCase;

    public CustomerController(
            CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase,
            CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase,
            FindUserByEmailUseCase findCustomerByEmail,
            FindUserByCpfUseCase findUserByCpfUseCase) {
        this.createCustomerWithCpfUseCase = createCustomerWithCpfUseCase;
        this.createCustomerWithNameAndEmailUseCase = createCustomerWithNameAndEmailUseCase;
        this.findUserByEmail = findCustomerByEmail;
        this.findUserByCpfUseCase = findUserByCpfUseCase;
    }

    @PostMapping("/cpf")
    public ResponseEntity<User> createCustomerWithCpf(@RequestBody @Valid CustomerCpfRequest customerCpfRequest) {
        final User customerCpf = CustomerMapper.toDomain(customerCpfRequest);
        final User customerSaved = this.createCustomerWithCpfUseCase.execute(customerCpf);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @PostMapping("/email")
    public ResponseEntity<User> createCustomerWithEmailAndName(@RequestBody @Valid CustomerEmailRequest customerEmailRequest) {
        final User customer = CustomerMapper.toDomain(customerEmailRequest);
        final User customerSaved = this.createCustomerWithNameAndEmailUseCase.execute(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @GetMapping("/email")
    public ResponseEntity<User> getCustomerByEmail(@RequestBody CustomerByEmailRequest customerEmailRequest) {
        final User customerSaved = this.findUserByEmail.execute(customerEmailRequest.email(), customerEmailRequest.userRole());
        return ResponseEntity.status(HttpStatus.OK).body(customerSaved);
    }

    @GetMapping("/cpf")
    public ResponseEntity<User> getCustomerByCpf(CustomerCpfRequest customerCpfRequest) {
        final User customerSaved = this.findUserByCpfUseCase.execute(customerCpfRequest.cpf(), customerCpfRequest.userRole());
        return ResponseEntity.status(HttpStatus.OK).body(customerSaved);
    }


}
