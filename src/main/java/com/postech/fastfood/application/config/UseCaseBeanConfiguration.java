package com.postech.fastfood.application.config;

import com.postech.fastfood.application.gateways.*;
import com.postech.fastfood.application.usecases.customer.CreateCustomerWithCpfUseCaseImpl;
import com.postech.fastfood.application.usecases.customer.CreateCustomerWithNameAndEmailUseCaseImpl;
import com.postech.fastfood.application.usecases.customer.FindCustomerByCpfUseCaseImpl;
import com.postech.fastfood.application.usecases.customer.FindCustomerByEmailUseCaseImpl;
import com.postech.fastfood.application.usecases.employee.CreateEmployeeUseCaseImpl;
import com.postech.fastfood.application.usecases.order.CreateOrderUseCaseImpl;
import com.postech.fastfood.application.usecases.order.ListOrdersByStatusUseCaseImpl;
import com.postech.fastfood.application.usecases.order.ListOrdersUseCaseImpl;
import com.postech.fastfood.application.usecases.order.UpdateOrderStatusUseCaseImpl;
import com.postech.fastfood.application.usecases.payment.CreatePaymentUseCaseImpl;
import com.postech.fastfood.application.usecases.payment.ProccessPaymentUseCaseImpl;
import com.postech.fastfood.application.usecases.payment.ProcessPaymentNotificationUseCaseImpl;
import com.postech.fastfood.application.usecases.payment.ValidateSignatureWebhookUseCaseImpl;
import com.postech.fastfood.application.usecases.product.CreateProductUseCaseImpl;
import com.postech.fastfood.application.usecases.product.DeleteProductUseCaseImpl;
import com.postech.fastfood.application.usecases.product.ListProductsByCategoryUseCaseImpl;
import com.postech.fastfood.application.usecases.product.ListProductsUseCaseImpl;
import com.postech.fastfood.application.usecases.product.UpdateProductUseCaseImpl;
import com.postech.fastfood.infrastructure.gateways.FindUserByCpfUseCase;
import com.postech.fastfood.infrastructure.gateways.FindUserByEmailUseCase;
import com.postech.fastfood.infrastructure.gateways.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.infrastructure.gateways.customer.CreateCustomerWithNameAndEmailUseCase;
import com.postech.fastfood.infrastructure.gateways.employee.CreateEmployeeUseCase;
import com.postech.fastfood.infrastructure.gateways.order.CreateOrderUseCase;
import com.postech.fastfood.infrastructure.gateways.order.ListOrdersByStatusUseCase;
import com.postech.fastfood.infrastructure.gateways.order.ListOrdersUseCase;
import com.postech.fastfood.infrastructure.gateways.order.UpdateOrderStatusUseCase;
import com.postech.fastfood.infrastructure.gateways.payment.CreatePaymentUseCase;
import com.postech.fastfood.infrastructure.gateways.payment.ProccessPaymentUseCase;
import com.postech.fastfood.infrastructure.gateways.payment.ProcessPaymentNotificationUseCase;
import com.postech.fastfood.infrastructure.gateways.product.CreateProductUseCase;
import com.postech.fastfood.infrastructure.gateways.product.DeleteProductUseCase;
import com.postech.fastfood.infrastructure.gateways.product.ListProductByCategoryUseCase;
import com.postech.fastfood.infrastructure.gateways.product.ListProductsUseCase;
import com.postech.fastfood.infrastructure.gateways.product.UpdateProductUseCase;
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
    public CreateOrderUseCase createOrderUseCase(OrderRepositoryPort orderRepositoryPort, CustomerRepositoryPort customerRepositoryPort,
                                                 ProductRepositoryPort productRepositoryPort) {
        return new CreateOrderUseCaseImpl(orderRepositoryPort, customerRepositoryPort, productRepositoryPort);
    }

    @Bean
    public ListOrdersByStatusUseCase listOrdersByStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new ListOrdersByStatusUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public ListOrdersUseCase listOrdersUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new ListOrdersUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new UpdateOrderStatusUseCaseImpl(orderRepositoryPort);
    }


    @Bean
    public ProcessPaymentNotificationUseCase processPaymentNotificationUseCase(OrderRepositoryPort orderRepositoryPort,
                                                                 UpdateOrderStatusUseCase updateOrderStatusUseCase,
                                                                 LoggerPort logger) {
        return new ProcessPaymentNotificationUseCaseImpl(orderRepositoryPort, updateOrderStatusUseCase, logger);
    }

}
