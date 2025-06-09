package com.postech.fastfood.core.service.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;
import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.ports.CustomerRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindCustomerByCpfUseCaseImplTest {
    @Mock
    CustomerRepositoryPort customerRepositoryPort;

    @InjectMocks
    FindCustomerByCpfUseCaseImpl useCase;

    @Test
    void deveRetornarClienteQuandoCpfExistir() {
        // Arrange
        String cpf = "12345678900";
        UserRole role = UserRole.ROLE_CUSTOMER;

        Customer cliente = new Customer();
        cliente.setId(UUID.randomUUID());
        cliente.setCpf(cpf);
        cliente.setName("João");
        cliente.setRole(role);

        when(customerRepositoryPort.findByCpf(cpf)).thenReturn(cliente);

        // Act
        Customer resultado = (Customer) useCase.execute(cpf);

        // Assert
        assertNotNull(resultado);
        assertEquals(cpf, resultado.getCpf());
        assertEquals("João", resultado.getName());
        assertEquals(UserRole.ROLE_CUSTOMER, resultado.getRole());
        verify(customerRepositoryPort, times(1)).findByCpf(cpf);
    }

    @Test
    void deveRetornarNullQuandoClienteNaoExiste() {
        // Arrange
        String cpf = "00000000000";
        UserRole role = UserRole.ROLE_CUSTOMER;

        when(customerRepositoryPort.findByCpf(cpf)).thenReturn(null);

        // Act
        var resultado = useCase.execute(cpf);

        // Assert
        assertNull(resultado);
        verify(customerRepositoryPort, times(1)).findByCpf(cpf);
    }
}
