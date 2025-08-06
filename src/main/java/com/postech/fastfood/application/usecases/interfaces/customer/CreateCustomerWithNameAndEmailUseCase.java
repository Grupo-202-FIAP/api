package com.postech.fastfood.application.usecases.interfaces.customer;

import com.postech.fastfood.domain.User;

public interface CreateCustomerWithNameAndEmailUseCase {
    User execute(User user);
}
