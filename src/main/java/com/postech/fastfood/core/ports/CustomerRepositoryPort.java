package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.Customer;
import java.util.UUID;

public interface CustomerRepositoryPort {
    Customer findByCpf(String cpf);

    Customer findByEmail(String email);

    Customer findById(UUID id);
}
