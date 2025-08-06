//package com.postech.fastfood.core.service.customer;
//
//import com.postech.fastfood.core.domain.Customer;
//import com.postech.fastfood.core.domain.enums.UserRole;
//import com.postech.fastfood.core.ports.UserRepositoryPort;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class FindCustomerByEmailUseCaseImplTest {
//
//    @Mock
//    UserRepositoryPort userRepositoryPort;
//
//    @InjectMocks
//    FindCustomerByEmailUseCaseImpl useCase; // mantém o nome conforme seu código, mas recomendo corrigir para FindCustomer...
//
//    @Test
//    void deveRetornarClienteQuandoEmailExistir() {
//        // Arrange
//        String email = "cliente@teste.com";
//        UserRole role = UserRole.ROLE_CUSTOMER;
//
//        Customer cliente = new Customer();
//        cliente.setId(UUID.randomUUID());
//        cliente.setEmail(email);
//        cliente.setName("Maria");
//        cliente.setRole(role);
//
//        when(userRepositoryPort.findByEmail(email, role)).thenReturn(cliente);
//
//        // Act
//        Customer resultado = (Customer) useCase.execute(email, role);
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(email, resultado.getEmail());
//        assertEquals("Maria", resultado.getName());
//        assertEquals(UserRole.ROLE_CUSTOMER, resultado.getRole());
//        verify(userRepositoryPort, times(1)).findByEmail(email, role);
//    }
//
//    @Test
//    void deveRetornarNullQuandoClienteNaoExiste() {
//        // Arrange
//        String email = "inexistente@teste.com";
//        UserRole role = UserRole.ROLE_CUSTOMER;
//
//        when(userRepositoryPort.findByEmail(email, role)).thenReturn(null);
//
//        // Act
//        var resultado = useCase.execute(email, role);
//
//        // Assert
//        assertNull(resultado);
//        verify(userRepositoryPort, times(1)).findByEmail(email, role);
//    }
//}