package com.postech.fastfood.core.usecase;

import com.postech.fastfood.core.domain.User;
import com.postech.fastfood.core.domain.enums.UserRole;

public interface FindUserByEmailUseCase {
    User execute(String email, UserRole userRole);
}
