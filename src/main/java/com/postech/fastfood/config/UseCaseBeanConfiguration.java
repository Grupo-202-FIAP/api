package com.postech.fastfood.config;

import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.service.*;
import com.postech.fastfood.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfiguration {

    @Bean
    public CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase(UserRepositoryPort userRepositoryPort){
        return new CreateCustomerWithNameAndEmailUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase(UserRepositoryPort userRepositoryPort){
        return new CreateCustomerWithCpfUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public FindUserByEmailUseCase findCustomerByEmailUseCase(UserRepositoryPort userRepositoryPort){
        return new FindUserByEmailUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public FindUserByCpfUseCase findCustomerByCpfUseCase (UserRepositoryPort userRepositoryPort){
        return new FindUserByCpfUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(UserRepositoryPort userRepositoryPort){
        return new CreateEmployeeUseCaseImpl(userRepositoryPort);
    }

}
