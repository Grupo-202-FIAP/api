package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.Customer;

public interface CustomerRepositoryPort {
    Customer findByCpf(String cpf);
}
