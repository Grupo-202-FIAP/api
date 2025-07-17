//package com.postech.fastfood.core.service.product;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//import com.postech.fastfood.core.domain.Product;
//import com.postech.fastfood.core.ports.ProductRepositoryPort;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class ListProductsUseCaseImplTest {
//
//    @Mock
//    private ProductRepositoryPort productRepositoryPort;
//
//    @InjectMocks
//    private ListProductsUseCaseImpl useCase;
//
//    @Test
//    void deveListarProdutosComSucesso() {
//        // Arrange
//        Product produto1 = new Product();
//        produto1.setName("Hambúrguer");
//
//        Product produto2 = new Product();
//        produto2.setName("Batata Frita");
//
//        List<Product> produtos = Arrays.asList(produto1, produto2);
//
//        when(productRepositoryPort.findAll()).thenReturn(produtos);
//
//        // Act
//        List<Product> resultado = useCase.execute();
//
//        // Assert
//        assertNotNull(resultado);
//        assertEquals(2, resultado.size());
//        assertEquals("Hambúrguer", resultado.get(0).getName());
//        assertEquals("Batata Frita", resultado.get(1).getName());
//
//        verify(productRepositoryPort, times(1)).findAll();
//    }
//}