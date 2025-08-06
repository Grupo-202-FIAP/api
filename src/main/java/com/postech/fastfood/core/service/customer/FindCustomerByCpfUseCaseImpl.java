package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.ports.CustomerRepositoryPort;
import com.postech.fastfood.core.usecase.FindUserByCpfUseCase;

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
