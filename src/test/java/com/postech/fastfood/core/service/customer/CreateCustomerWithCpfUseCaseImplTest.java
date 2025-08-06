//package com.postech.fastfood.core.service.customer;
//
//import com.postech.fastfood.core.domain.Employee;
//import com.postech.fastfood.core.domain.User;
//import com.postech.fastfood.core.exception.FastFoodException;
//import com.postech.fastfood.core.ports.UserRepositoryPort;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.argThat;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class CreateCustomerWithCpfUseCaseImplTest {
//
//    @Mock
//    private UserRepositoryPort userRepositoryPort;
//    @InjectMocks
//    private CreateCustomerWithCpfUseCaseImpl createCustomerWithCpfUseCase;
//
//
//    @Test
//    public void testExecute_shouldFormatCpfAndSaveUser() {
//        // Given
//        User user = new Employee();
//        user.setCpf("123.456.789-00");
//
//        User savedUser = new Employee();
//        savedUser.setCpf("12345678900");
//
//        when(userRepositoryPort.save(any(User.class))).thenReturn(savedUser);
//
//        // When
//        User result = createCustomerWithCpfUseCase.execute(user);
//
//        // Then
//        assertEquals("12345678900", result.getCpf());
//        verify(userRepositoryPort).save(argThat(u -> u.getCpf().equals("12345678900")));
//    }
//
//    @Test
//    public void testExecute_shouldThrowCpfAlreadyInUseException() {
//        // Given
//        User user = new User() {
//        };
//        user.setCpf("123.456.789-00");
//
//        when(userRepositoryPort.save(any(User.class))).thenThrow(new DataIntegrityViolationException("Duplicate CPF"));
//
//        // When & Then
//        FastFoodException exception = assertThrows(FastFoodException.class, () -> {
//            createCustomerWithCpfUseCase.execute(user);
//        });
//
//        assertEquals("Duplicate CPF", exception.getMessage());
//    }
//
//}