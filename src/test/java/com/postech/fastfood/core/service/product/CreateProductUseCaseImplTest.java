package com.postech.fastfood.core.service.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private CreateProductUseCaseImpl useCase;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product();
        product.setName("Hambúrguer Personalizado");
        // Você pode setar outros atributos caso queira, ex: categoria, preço etc.
    }

    @Test
    void deveCriarProdutoComSucesso() {
        // Arrange
        when(productRepositoryPort.save(any(Product.class))).thenReturn(product);

        // Act
        Product resultado = useCase.execute(product);

        // Assert
        assertNotNull(resultado);
        assertEquals("Hambúrguer Personalizado", resultado.getName());
        verify(productRepositoryPort).save(any(Product.class));
    }

    @Test
    void deveLancarExcecaoQuandoNomeDuplicado() {
        // Arrange
        var excecao = new DataIntegrityViolationException("violates product_name_unique_constraint");

        when(productRepositoryPort.save(any(Product.class))).thenThrow(excecao);

        // Act & Assert
        FastFoodException exception = assertThrows(
                FastFoodException.class,
                () -> useCase.execute(product)
        );

        assertEquals("Product name already in use", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        verify(productRepositoryPort).save(any(Product.class));
    }
}