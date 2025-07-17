package com.postech.fastfood.infrastructure.gateways.customer;

import com.postech.fastfood.domain.User;

public interface CreateCustomerWithNameAndEmailUseCase {
    User execute(User user);
}
