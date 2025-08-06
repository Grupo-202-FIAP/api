package com.postech.fastfood.application.usecases.interfaces.customer;

import com.postech.fastfood.domain.User;

public interface CreateCustomerWithCpfUseCase {
    User execute(User user);
}
