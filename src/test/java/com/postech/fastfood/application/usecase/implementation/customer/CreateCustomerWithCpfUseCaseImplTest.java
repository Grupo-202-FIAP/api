package com.postech.fastfood.application.usecase.implementation.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.postech.fastfood.application.gateway.UserRepositoryPort;
import com.postech.fastfood.domain.Customer;
import com.postech.fastfood.domain.User;
import com.postech.fastfood.domain.enums.UserRole;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCustomerWithCpfUseCaseImplTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    CreateCustomerWithCpfUseCaseImpl createCustomerWithCpfUseCaseImpl;

    @Test
    void shouldFormatCpfSetRoleAndReturnSavedCustomer_onSuccess() {
        Customer input = new Customer();
        input.setId(UUID.randomUUID());
        input.setCpf("123.456.789-00");
        input.setRole(UserRole.ROLE_ADMIN);

        Customer saved = new Customer();
        saved.setId(input.getId());
        saved.setCpf("12345678900");
        saved.setRole(UserRole.ROLE_CUSTOMER);

        when(userRepositoryPort.save(any(User.class))).thenReturn(saved);
        User result = createCustomerWithCpfUseCaseImpl.execute(input);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryPort).save(captor.capture());

        User resultCaptured = captor.getValue();

        assertEquals("12345678900", resultCaptured.getCpf());
        assertEquals(UserRole.ROLE_CUSTOMER, resultCaptured.getRole());

        assertSame(saved, result);
    }

    @Test
    void shouldCreateAnCustomerGuest_whenCpfIsNull(){
        Customer input = new Customer();
        input.setId(UUID.randomUUID());
        input.setCpf(null);
        input.setRole(UserRole.ROLE_ADMIN);

        Customer saved = new Customer();
        saved.setId(input.getId());
        saved.setRole(UserRole.ROLE_GUEST);

        when(userRepositoryPort.save(any(User.class))).thenReturn(saved);
        User result = createCustomerWithCpfUseCaseImpl.execute(input);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryPort).save(captor.capture());

        User resultCaptured = captor.getValue();

        assertNull(resultCaptured.getCpf());
        assertEquals(UserRole.ROLE_GUEST, resultCaptured.getRole());

        assertSame(saved, result);
    }





    }
