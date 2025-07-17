package com.postech.fastfood.application.config;


import com.postech.fastfood.application.gateways.CustomerRepository;
import com.postech.fastfood.application.gateways.OrderRepository;
import com.postech.fastfood.application.gateways.PasswordEncoder;
import com.postech.fastfood.application.gateways.PaymentRepository;
import com.postech.fastfood.application.gateways.ProductRepository;
import com.postech.fastfood.application.gateways.UserRepository;
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
    public CreateCustomerWithNameAndEmailUseCase createCustomerWithNameAndEmailUseCase(UserRepository userRepository) {
        return new CreateCustomerWithNameAndEmailUseCaseImpl(userRepository);
    }

    @Bean
    public CreateCustomerWithCpfUseCase createCustomerWithCpfUseCase(UserRepository userRepository) {
        return new CreateCustomerWithCpfUseCaseImpl(userRepository);
    }

    @Bean
    public FindUserByEmailUseCase findCustomerByEmailUseCase(UserRepository userRepository) {
        return new FindCustomerByEmailUseCaseImpl(userRepository);
    }

    @Bean
    public FindUserByCpfUseCase findCustomerByCpfUseCase(CustomerRepository customerRepository) {
        return new FindCustomerByCpfUseCaseImpl(customerRepository);
    }

    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new CreateEmployeeUseCaseImpl(userRepository, passwordEncoder);
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentRepository paymentRepository) {
        return new CreatePaymentUseCaseImpl(paymentRepository);
    }

    @Bean
    public ProccessPaymentUseCase savePayment(PaymentRepository paymentRepository) {
        return new ProccessPaymentUseCaseImpl(paymentRepository);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepository productRepository) {
        return new CreateProductUseCaseImpl(productRepository);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepository productRepository) {
        return new UpdateProductUseCaseImpl(productRepository);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepository productRepository) {
        return new DeleteProductUseCaseImpl(productRepository);
    }

    @Bean
    public ListProductsUseCase listProductsUseCase(ProductRepository productRepository) {
        return new ListProductsUseCaseImpl(productRepository);
    }

    @Bean
    public ListProductByCategoryUseCase listProductByCategoryUseCase(ProductRepository productRepository) {
        return new ListProductsByCategoryUseCaseImpl(productRepository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderRepository orderRepository, CustomerRepository customerRepository,
                                                 ProductRepository productRepository) {
        return new CreateOrderUseCaseImpl(orderRepository, customerRepository, productRepository);
    }

    @Bean
    public ListOrdersByStatusUseCase listOrdersByStatusUseCase(OrderRepository orderRepository) {
        return new ListOrdersByStatusUseCaseImpl(orderRepository);
    }

    @Bean
    public ListOrdersUseCase listOrdersUseCase(OrderRepository orderRepository) {
        return new ListOrdersUseCaseImpl(orderRepository);
    }

    @Bean
    public UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderRepository orderRepository) {
        return new UpdateOrderStatusUseCaseImpl(orderRepository);
    }

}
