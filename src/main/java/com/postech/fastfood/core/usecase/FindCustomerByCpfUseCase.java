package com.postech.fastfood.core.usecase;

import com.postech.fastfood.core.domain.User;

public interface FindCustomerByCpfUseCase {
    User execute(String cpf);
}
