package com.postech.fastfood.infrastructure.gateways.customer;

import com.postech.fastfood.domain.User;

public interface CreateCustomerWithCpfUseCase {
    User execute(User user);
}
