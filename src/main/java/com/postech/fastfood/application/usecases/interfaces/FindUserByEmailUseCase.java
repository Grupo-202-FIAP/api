package com.postech.fastfood.application.usecases.interfaces;

import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;

public interface FindUserByEmailUseCase {
    User execute(String email, UserRole userRole);
}
