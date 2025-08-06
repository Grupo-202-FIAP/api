package com.postech.fastfood.application.usecases.interfaces.customer;

import com.postech.fastfood.domain.User;
import java.util.UUID;

public interface FindCustomerByIdUseCase {
    User execute(UUID id);
}
