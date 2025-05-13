package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.User;

public interface UserRepositoryPort {
     User saveCustomer(User user);

//     User findCustomerByCpf(String cpf);
//
//     User findCustomerByEmail(String email);
}
