package com.postech.fastfood.application.gateways;

import com.postech.fastfood.domain.Customer;
import java.util.UUID;

public interface CustomerRepositoryPort {
    Customer findByCpf(String cpf);

    Customer findByEmail(String email);

    Customer findById(UUID id);
}
