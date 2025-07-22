package com.postech.fastfood.infrastructure.gateways.customer;

import com.postech.fastfood.domain.User;
import java.util.UUID;

public interface FindCustomerByIdUseCase {
    User execute(UUID id);
}
