package com.postech.fastfood.core.usecase;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;

public interface FindUserByCpfUseCase {
    User execute(String cpf, UserRole userRole);
}
