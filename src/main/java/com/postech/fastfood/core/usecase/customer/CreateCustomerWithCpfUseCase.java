package com.postech.fastfood.core.usecase.customer;

import com.postech.fastfood.core.domain.User;

public interface CreateCustomerWithCpfUseCase {
    User execute(User user);
}
