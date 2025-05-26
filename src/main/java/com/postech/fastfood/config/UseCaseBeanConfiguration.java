package com.postech.fastfood.config;

import com.postech.fastfood.core.ports.PasswordEncoderPort;
import com.postech.fastfood.core.ports.PaymentRepositoryPort;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.service.customer.CreateCustomerWithCpfUseCaseImpl;
import com.postech.fastfood.core.service.customer.CreateCustomerWithNameAndEmailUseCaseImpl;
import com.postech.fastfood.core.service.customer.FindCustomerByCpfUseCaseImpl;
import com.postech.fastfood.core.service.customer.FindCustomerrByEmailUseCaseImpl;
import com.postech.fastfood.core.service.employee.CreateEmployeeUseCaseImpl;
import com.postech.fastfood.core.service.payment.CreatePaymentUseCaseImpl;
import com.postech.fastfood.core.service.payment.ProccessPaymentUseCaseImpl;
import com.postech.fastfood.core.usecase.FindUserByCpfUseCase;
import com.postech.fastfood.core.usecase.FindUserByEmailUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithNameAndEmailUseCase;
import com.postech.fastfood.core.usecase.employee.CreateEmployeeUseCase;
import com.postech.fastfood.core.usecase.payment.CreatePaymentUseCase;
import com.postech.fastfood.core.usecase.payment.ProccessPaymentUseCase;
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
    public CreatePaymentUseCase createPaymentUseCase(PaymentRepositoryPort paymentRepositoryPort) {
        return new CreatePaymentUseCaseImpl(paymentRepositoryPort);
    }

    @Bean
    public ProccessPaymentUseCase savePayment(PaymentRepositoryPort paymentRepositoryPort) {
        return new ProccessPaymentUseCaseImpl(paymentRepositoryPort) {
        };
    }


}
