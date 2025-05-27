package com.postech.fastfood.config;

import com.postech.fastfood.core.ports.PasswordEncoderPort;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.service.customer.CreateCustomerWithCpfUseCaseImpl;
import com.postech.fastfood.core.service.customer.CreateCustomerWithNameAndEmailUseCaseImpl;
import com.postech.fastfood.core.service.customer.FindCustomerByCpfUseCaseImpl;
import com.postech.fastfood.core.service.customer.FindCustomerrByEmailUseCaseImpl;
import com.postech.fastfood.core.service.employee.CreateEmployeeUseCaseImpl;
import com.postech.fastfood.core.service.product.*;
import com.postech.fastfood.core.usecase.FindUserByCpfUseCase;
import com.postech.fastfood.core.usecase.FindUserByEmailUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithNameAndEmailUseCase;
import com.postech.fastfood.core.usecase.employee.CreateEmployeeUseCase;
import com.postech.fastfood.core.usecase.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfiguration {

    @Bean
    public CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase(UserRepositoryPort userRepositoryPort) {
        return new CreateCustomerWithNameAndEmailUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase(UserRepositoryPort userRepositoryPort) {
        return new CreateCustomerWithCpfUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public FindUserByEmailUseCase findCustomerByEmailUseCase(UserRepositoryPort userRepositoryPort) {
        return new FindCustomerrByEmailUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public FindUserByCpfUseCase findCustomerByCpfUseCase(UserRepositoryPort userRepositoryPort) {
        return new FindCustomerByCpfUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        return new CreateEmployeeUseCaseImpl(userRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new CreateProductUseCaseImpl(productRepositoryPort);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new UpdateProductUseCaseImpl(productRepositoryPort);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new DeleteProductUseCaseImpl(productRepositoryPort);
    }

    @Bean
    public ListProductsUseCase listProductsUseCase(ProductRepositoryPort productRepositoryPort) {
        return new ListProductsUseCaseImpl(productRepositoryPort);
    }

    @Bean
    public ListProductByCategoryUseCase listProductByCategoryUseCase(ProductRepositoryPort productRepositoryPort) {
        return new ListProductsByCategoryUseCaseImpl(productRepositoryPort);
    }

}
