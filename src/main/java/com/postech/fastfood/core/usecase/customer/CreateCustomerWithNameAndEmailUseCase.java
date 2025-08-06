package com.postech.fastfood.core.usecase.customer;

import com.postech.fastfood.core.domain.User;

public interface CreateCustomerWithNameAndEmailUseCase {
    User execute(User user);
}
