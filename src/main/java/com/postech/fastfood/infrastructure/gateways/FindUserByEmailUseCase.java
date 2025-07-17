package com.postech.fastfood.infrastructure.gateways;

import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;

public interface FindUserByEmailUseCase {
    User execute(String email, UserRole userRole);
}
