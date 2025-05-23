package com.postech.fastfood.core.service.product;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.postech.fastfood.core.ports.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteProductUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private DeleteProductUseCaseImpl useCase;

    @Test
    void deveExcluirProdutoComSucesso() {
        // Arrange
        Long idProduto = 1L;

        // Act
        useCase.execute(idProduto);

        // Assert
        verify(productRepositoryPort, times(1)).delete(idProduto);
    }
}