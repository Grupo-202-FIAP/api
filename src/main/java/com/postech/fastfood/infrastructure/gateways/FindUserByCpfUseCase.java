package com.postech.fastfood.infrastructure.gateways;

import com.postech.fastfood.domain.User;

public interface FindUserByCpfUseCase {
    User execute(String cpf);
}
