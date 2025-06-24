package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerByEmailRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.application.mapper.CustomerMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.ports.LoggerPort;
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
@RequestMapping("/customer")
public class CustomerController {

    private final CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase;
    private final CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase;
    private final FindUserByEmailUseCase findUserByEmail;
    private final FindUserByCpfUseCase findUserByCpfUseCase;
    private final LoggerPort logger;

    public CustomerController(
            CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase,
            CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase,
            FindUserByEmailUseCase findCustomerByEmail,
            FindUserByCpfUseCase findUserByCpfUseCase,
            LoggerPort logger) {
        this.createCustomerWithCpfUseCase = createCustomerWithCpfUseCase;
        this.createCustomerWithNameAndEmailUseCase = createCustomerWithNameAndEmailUseCase;
        this.findUserByEmail = findCustomerByEmail;
        this.findUserByCpfUseCase = findUserByCpfUseCase;
        this.logger = logger;
    }

    @PostMapping("/cpf")
    public ResponseEntity<User> createCustomerWithCpf(@RequestBody @Valid CustomerCpfRequest customerCpfRequest) {
        logger.info("[Customer] Iniciando criação de cliente por CPF");
        final User customerCpf = CustomerMapper.toDomain(customerCpfRequest);
        final User customerSaved = this.createCustomerWithCpfUseCase.execute(customerCpf);
        logger.info("[Customer] Cliente criado com sucesso por CPF: id={}", customerSaved.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @PostMapping("/email")
    public ResponseEntity<User> createCustomerWithEmailAndName(@RequestBody @Valid CustomerEmailRequest customerEmailRequest) {
        logger.info("[Customer] Iniciando criação de cliente por e-mail");
        final User customer = CustomerMapper.toDomain(customerEmailRequest);
        final User customerSaved = this.createCustomerWithNameAndEmailUseCase.execute(customer);
        logger.info("[Customer] Cliente criado com sucesso por e-mail: id={}", customerSaved.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @GetMapping("/email")
    public ResponseEntity<User> getCustomerByEmail(@RequestBody CustomerByEmailRequest customerEmailRequest) {
        logger.info("[Customer] Buscando cliente por e-mail");
        final User userFound = this.findUserByEmail.execute(customerEmailRequest.email(), customerEmailRequest.userRole());
        logger.info("[Customer] Cliente encontrado por e-mail: {}", userFound.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userFound);
    }

    @GetMapping("/cpf")
    public ResponseEntity<User> getCustomerByCpf(CustomerCpfRequest customerCpfRequest) {
        logger.info("[Customer] Buscando cliente por CPF");
        final User userFound = this.findUserByCpfUseCase.execute(customerCpfRequest.cpf());
        logger.info("[Customer] Cliente encontrado por CPF: id={}", userFound.getId());
        return ResponseEntity.status(HttpStatus.OK).body(userFound);
    }
}
