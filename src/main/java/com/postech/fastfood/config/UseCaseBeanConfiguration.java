package com.postech.fastfood.config;

import com.postech.fastfood.core.ports.CustomerRepositoryPort;
import com.postech.fastfood.core.ports.OrderRepositoryPort;
import com.postech.fastfood.core.ports.PasswordEncoderPort;
import com.postech.fastfood.core.ports.PaymentRepositoryPort;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import com.postech.fastfood.core.service.customer.CreateCustomerWithCpfUseCaseImpl;
import com.postech.fastfood.core.service.customer.CreateCustomerWithNameAndEmailUseCaseImpl;
import com.postech.fastfood.core.service.customer.FindCustomerByCpfUseCaseImpl;
import com.postech.fastfood.core.service.customer.FindCustomerByEmailUseCaseImpl;
import com.postech.fastfood.core.service.employee.CreateEmployeeUseCaseImpl;
import com.postech.fastfood.core.service.order.CreateOrderUseCaseImpl;
import com.postech.fastfood.core.service.order.ListOrdersByStatusUseCaseImpl;
import com.postech.fastfood.core.service.order.ListOrdersUseCaseImpl;
import com.postech.fastfood.core.service.payment.CreatePaymentUseCaseImpl;
import com.postech.fastfood.core.service.payment.ProccessPaymentUseCaseImpl;
import com.postech.fastfood.core.service.product.CreateProductUseCaseImpl;
import com.postech.fastfood.core.service.product.DeleteProductUseCaseImpl;
import com.postech.fastfood.core.service.product.ListProductsByCategoryUseCaseImpl;
import com.postech.fastfood.core.service.product.ListProductsUseCaseImpl;
import com.postech.fastfood.core.service.product.UpdateProductUseCaseImpl;
import com.postech.fastfood.core.usecase.FindUserByCpfUseCase;
import com.postech.fastfood.core.usecase.FindUserByEmailUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.core.usecase.customer.CreateCustomerWithNameAndEmailUseCase;
import com.postech.fastfood.core.usecase.employee.CreateEmployeeUseCase;
import com.postech.fastfood.core.usecase.order.CreateOrderUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersByStatusUseCase;
import com.postech.fastfood.core.usecase.order.ListOrdersUseCase;
import com.postech.fastfood.core.usecase.payment.CreatePaymentUseCase;
import com.postech.fastfood.core.usecase.payment.ProccessPaymentUseCase;
import com.postech.fastfood.core.usecase.product.CreateProductUseCase;
import com.postech.fastfood.core.usecase.product.DeleteProductUseCase;
import com.postech.fastfood.core.usecase.product.ListProductByCategoryUseCase;
import com.postech.fastfood.core.usecase.product.ListProductsUseCase;
import com.postech.fastfood.core.usecase.product.UpdateProductUseCase;
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
        return new FindCustomerByEmailUseCaseImpl(userRepositoryPort);
    }

    @Bean
    public FindUserByCpfUseCase findCustomerByCpfUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return new FindCustomerByCpfUseCaseImpl(customerRepositoryPort);
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
        return new ProccessPaymentUseCaseImpl(paymentRepositoryPort);
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

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new CreateOrderUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public ListOrdersByStatusUseCase listOrdersByStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new ListOrdersByStatusUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public ListOrdersUseCase listOrdersUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new ListOrdersUseCaseImpl(orderRepositoryPort);
    }

}
