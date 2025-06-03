package com.postech.fastfood.core.service.customer;

import com.postech.fastfood.core.domain.Customer;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindCustomerByCpfUseCaseImplTest {

    @Mock
    UserRepositoryPort userRepositoryPort;

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

        when(userRepositoryPort.findByCpf(cpf, role)).thenReturn(cliente);

        // Act
        Customer resultado = (Customer) useCase.execute(cpf);

        // Assert
        assertNotNull(resultado);
        assertEquals(cpf, resultado.getCpf());
        assertEquals("João", resultado.getName());
        assertEquals(UserRole.ROLE_CUSTOMER, resultado.getRole());
        verify(userRepositoryPort, times(1)).findByCpf(cpf, role);
    }

    @Test
    void deveRetornarNullQuandoClienteNaoExiste() {
        // Arrange
        String cpf = "00000000000";
        UserRole role = UserRole.ROLE_CUSTOMER;

        when(userRepositoryPort.findByCpf(cpf, role)).thenReturn(null);

        // Act
        var resultado = useCase.execute(cpf);

        // Assert
        assertNull(resultado);
        verify(userRepositoryPort, times(1)).findByCpf(cpf, role);
    }
}
