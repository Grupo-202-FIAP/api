package com.postech.fastfood.application.usecases.customer;

import com.postech.fastfood.application.gateways.CustomerRepository;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.infrastructure.gateways.FindUserByCpfUseCase;

public class FindCustomerByCpfUseCaseImpl implements FindUserByCpfUseCase {

    private final CustomerRepository customerRepository;

    public FindCustomerByCpfUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public User execute(String cpf) {
        return this.customerRepository.findByCpf(cpf);
    }

}
