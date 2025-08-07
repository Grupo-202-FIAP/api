package com.postech.fastfood.application.usecases.interfaces;

import com.postech.fastfood.domain.User;

public interface FindUserByCpfUseCase {
    User execute(String cpf);
}
