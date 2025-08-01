package com.postech.fastfood.core.usecase.customer;

import com.postech.fastfood.core.domain.User;
import java.util.UUID;

public interface FindCustomerByIdUseCase {
    User execute(UUID id);
}
