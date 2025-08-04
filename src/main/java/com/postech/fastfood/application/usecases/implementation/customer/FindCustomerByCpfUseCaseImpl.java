package com.postech.fastfood.application.usecases.implementation.customer;

import com.postech.fastfood.application.gateways.CustomerRepositoryPort;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.application.usecases.interfaces.FindUserByCpfUseCase;

public class FindCustomerByCpfUseCaseImpl implements FindUserByCpfUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public FindCustomerByCpfUseCaseImpl(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public User execute(String cpf) {
        return this.customerRepositoryPort.findByCpf(cpf);
    }

}
