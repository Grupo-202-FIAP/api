package com.postech.fastfood.core.usecase;

import com.postech.fastfood.core.domain.User;

public interface FindCustomerByEmailUseCase {
    User execute(String email);
}
