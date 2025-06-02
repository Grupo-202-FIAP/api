package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerWithNameAndEmailUseCaseImplTest {

    @Mock
    UserRepositoryPort userRepositoryPort;

    @InjectMocks
    CreateCustomerWithNameAndEmailUseCaseImpl useCase;

    @Test
    void deveSalvarClienteComSucesso() {
        // Arrange
        Customer customer = new Customer();
        customer.setEmail("cliente@teste.com");
        customer.setName("Cliente");

        UUID clienteId = UUID.randomUUID();

        Customer clienteSalvo = new Customer();
        clienteSalvo.setId(clienteId);
        clienteSalvo.setEmail("cliente@teste.com");
        clienteSalvo.setName("Cliente");
        clienteSalvo.setRole(UserRole.ROLE_CUSTOMER);

        when(userRepositoryPort.save(any(Customer.class))).thenReturn(clienteSalvo);

        // Act
        Customer resultado = (Customer) useCase.execute(customer);

        // Assert
        assertNotNull(resultado);
        assertEquals(clienteId, resultado.getId());
        assertEquals(UserRole.ROLE_CUSTOMER, resultado.getRole());
        verify(userRepositoryPort, times(1)).save(any(Customer.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        // Arrange
        Customer customer = new Customer();
        customer.setEmail("duplicado@teste.com");
        customer.setName("Duplicado");

        when(userRepositoryPort.save(any(Customer.class)))
                .thenThrow(new DataIntegrityViolationException("E-mail já existe"));

        // Act & Assert
        FastFoodException exception = assertThrows(FastFoodException.class, () -> {
            useCase.execute(customer);
        });

        assertEquals("E-mail já existe", exception.getMessage());
        verify(userRepositoryPort, times(1)).save(any(Customer.class));
    }
}
