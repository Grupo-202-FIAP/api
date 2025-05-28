package com.postech.fastfood.core.service.employee;

import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.domain.enums.UserRole;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.PasswordEncoderPort;
import com.postech.fastfood.core.ports.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeUseCaseImplTest {

    @Mock
    UserRepositoryPort userRepositoryPort;

    @Mock
    PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    CreateEmployeeUseCaseImpl useCase;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setName("Funcionário");
        employee.setEmail("funcionario@empresa.com");
        employee.setCpf("123.456.789-00");
        employee.setPassword("senha123");
        employee.setRole(UserRole.ROLE_ADMIN);
    }

    @Test
    void deveCriarFuncionarioComSucesso() {
        // Arrange
        String senhaCodificada = "senhaCodificada";
        when(passwordEncoderPort.encode("senha123")).thenReturn(senhaCodificada);

        Employee funcionarioSalvo = new Employee();
        funcionarioSalvo.setId(employee.getId());
        funcionarioSalvo.setCpf("12345678900");
        funcionarioSalvo.setEmail(employee.getEmail());
        funcionarioSalvo.setName(employee.getName());
        funcionarioSalvo.setPassword(senhaCodificada);
        funcionarioSalvo.setRole(UserRole.ROLE_ADMIN);

        when(userRepositoryPort.save(any(Employee.class))).thenReturn(funcionarioSalvo);

        // Act
        Employee resultado = useCase.execute(employee);

        // Assert
        assertNotNull(resultado);
        assertEquals("12345678900", resultado.getCpf());
        assertEquals(senhaCodificada, resultado.getPassword());
        verify(userRepositoryPort).save(any(Employee.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        // Arrange
        var causa = new SQLException("violates email_unique_constraint");
        var excecao = new DataIntegrityViolationException("Erro de integridade", causa);

        when(passwordEncoderPort.encode(anyString())).thenReturn("qualquerSenha");
        when(userRepositoryPort.save(any(Employee.class))).thenThrow(excecao);

        // Act & Assert
        FastFoodException exception = assertThrows(
                FastFoodException.class,
                () -> useCase.execute(employee)
        );

        assertEquals("Email já cadastrado", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExiste() {
        // Arrange
        var causa = new SQLException("violates cpf_unique_constraint");
        var excecao = new DataIntegrityViolationException("Erro de integridade", causa);

        when(passwordEncoderPort.encode(anyString())).thenReturn("qualquerSenha");
        when(userRepositoryPort.save(any(Employee.class))).thenThrow(excecao);

        // Act & Assert
        FastFoodException exception = assertThrows(
                FastFoodException.class,
                () -> useCase.execute(employee)
        );

        assertEquals("CPF já cadastrado", exception.getMessage());
    }
}
