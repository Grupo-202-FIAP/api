package com.postech.fastfood.core.usecase;

import com.postech.fastfood.core.domain.User;

public interface FindUserByCpfUseCase {
    User execute(String cpf);
}
