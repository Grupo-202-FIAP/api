package com.postech.fastfood.application.config;


import com.postech.fastfood.application.gateways.UserRepositoryPort;
import com.postech.fastfood.application.gateways.CustomerRepositoryPort;
import com.postech.fastfood.application.gateways.OrderRepositoryPort;
import com.postech.fastfood.application.gateways.PaymentRepositoryPort;
import com.postech.fastfood.application.gateways.PasswordEncoderPort;
import com.postech.fastfood.application.gateways.ProductRepositoryPort;
import com.postech.fastfood.application.gateways.MercadoPagoPort;
import com.postech.fastfood.application.gateways.LoggerPort;
import com.postech.fastfood.application.usecases.implementation.customer.CreateCustomerWithCpfUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.customer.CreateCustomerWithNameAndEmailUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.customer.FindCustomerByCpfUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.customer.FindCustomerByEmailUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.employee.CreateEmployeeUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.order.CreateOrderUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.order.ListOrdersByStatusUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.order.ListOrdersUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.order.UpdateOrderStatusUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.payment.CheckPaymentStatusUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.payment.GenerateQrCodePaymentUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.payment.ProccessPaymentUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.payment.ProcessPaymentNotificationUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.product.CreateProductUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.product.DeleteProductUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.product.ListProductsByCategoryUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.product.ListProductsUseCaseImpl;
import com.postech.fastfood.application.usecases.implementation.product.UpdateProductUseCaseImpl;
import com.postech.fastfood.application.usecases.interfaces.FindUserByCpfUseCase;
import com.postech.fastfood.application.usecases.interfaces.FindUserByEmailUseCase;
import com.postech.fastfood.application.usecases.interfaces.GenerateQrCodePaymentUseCase;
import com.postech.fastfood.application.usecases.interfaces.customer.CreateCustomerWithCpfUseCase;
import com.postech.fastfood.application.usecases.interfaces.customer.CreateCustomerWithNameAndEmailUseCase;
import com.postech.fastfood.application.usecases.interfaces.employee.CreateEmployeeUseCase;
import com.postech.fastfood.application.usecases.interfaces.order.CreateOrderUseCase;
import com.postech.fastfood.application.usecases.interfaces.order.ListOrdersByStatusUseCase;
import com.postech.fastfood.application.usecases.interfaces.order.ListOrdersUseCase;
import com.postech.fastfood.application.usecases.interfaces.order.UpdateOrderStatusUseCase;
import com.postech.fastfood.application.usecases.interfaces.payment.CheckPaymentStatusUseCase;
import com.postech.fastfood.application.usecases.interfaces.payment.ProccessPaymentUseCase;
import com.postech.fastfood.application.usecases.interfaces.payment.ProcessPaymentNotificationUseCase;
import com.postech.fastfood.application.usecases.interfaces.product.CreateProductUseCase;
import com.postech.fastfood.application.usecases.interfaces.product.DeleteProductUseCase;
import com.postech.fastfood.application.usecases.interfaces.product.ListProductByCategoryUseCase;
import com.postech.fastfood.application.usecases.interfaces.product.ListProductsUseCase;
import com.postech.fastfood.application.usecases.interfaces.product.UpdateProductUseCase;
import com.postech.fastfood.infrastructure.http.mercadopago.security.MercadoPagoWebhookSignatureValidator;
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
    public ProccessPaymentUseCase proccessPaymentUseCase(PaymentRepositoryPort paymentRepositoryPort) {
        return new ProccessPaymentUseCaseImpl(paymentRepositoryPort);
    }

    @Bean
    public GenerateQrCodePaymentUseCase generateQrCodePaymentUseCase(
            OrderRepositoryPort orderRepositoryPort,
            MercadoPagoPort mercadoPagoPort,
            LoggerPort loggerPort) {
        return new GenerateQrCodePaymentUseCaseImpl(orderRepositoryPort,mercadoPagoPort, loggerPort);
    }

    @Bean
    public ProcessPaymentNotificationUseCase processPaymentNotificationUseCase(
            OrderRepositoryPort orderRepositoryPort,
            UpdateOrderStatusUseCase updateOrderStatusUseCase,
            MercadoPagoWebhookSignatureValidator mercadoPagoWebhookSignatureValidator,
            LoggerPort logger) {
        return new ProcessPaymentNotificationUseCaseImpl(
                orderRepositoryPort,
                updateOrderStatusUseCase,
                mercadoPagoWebhookSignatureValidator,
                logger);
    }

    @Bean
    public CheckPaymentStatusUseCase checkPaymentStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new CheckPaymentStatusUseCaseImpl(orderRepositoryPort);
    }

}
