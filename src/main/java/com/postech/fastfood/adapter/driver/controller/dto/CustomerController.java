package com.postech.fastfood.adapter.driver.controller.dto;

import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.application.mapper.UserMapper;
import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.usecase.CreateCustomerWithCpfUseCase;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase;

    public CustomerController(CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase) {
        this.createCustomerWithCpfUseCase = createCustomerWithCpfUseCase;
    }

    @PostMapping("/cpf")
    public ResponseEntity<User> createCustomerWithCpf(@RequestBody @Valid CustomerCpfRequest customerCpfRequest){
        User customerCpf = UserMapper.toDomain(customerCpfRequest);
        User customerSaved = this.createCustomerWithCpfUseCase.execute(customerCpf);
        return ResponseEntity.status(201).body(customerSaved);
    }


}
