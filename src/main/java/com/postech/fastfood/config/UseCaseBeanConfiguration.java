package com.postech.fastfood.config;

import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.service.CreateCustomerWithCpfUseCaseImpl;
import com.postech.fastfood.core.service.CreateCustomerWithNameAndEmailUseCaseImpl;
import com.postech.fastfood.core.usecase.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.CreateCustomerWithNameAndEmailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfiguration {
//    @Bean
//    public CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase(UserRepositoryPort userRepositoryPort){
//        return new CreateCustomerWithNameAndEmailUseCaseImpl(userRepositoryPort);
//    }

    @Bean
    public CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase(UserRepositoryPort userRepositoryPort){
        return new CreateCustomerWithCpfUseCaseImpl(userRepositoryPort);
    }
}
