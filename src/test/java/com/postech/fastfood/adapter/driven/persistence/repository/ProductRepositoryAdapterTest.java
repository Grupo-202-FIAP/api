package com.postech.fastfood.adapter.driven.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.postech.fastfood.adapter.driven.persistence.repository.employee.IEmployeeEntityRepository;
import com.postech.fastfood.adapter.driven.persistence.repository.product.IProductRepository;
import com.postech.fastfood.adapter.driven.persistence.repository.product.ProductRepositoryAdapter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.postech.fastfood.adapter.driven.persistence.entity.EmployeeEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.ProductEntity;
import com.postech.fastfood.application.mapper.EmployeeMapper;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.exception.FastFoodException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryAdapterTest {

    @Mock
    private IProductRepository productRepository;

    @Mock
    private IEmployeeEntityRepository employeeEntityRepository;

    @InjectMocks
    private ProductRepositoryAdapter adapter;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private EmployeeMapper employeeMapper;

    private Product productDomain;
    private Employee employeeDomain;
    private EmployeeEntity employeeEntity;
    private ProductEntity productEntity;
    private ProductEntity savedEntity;

    private UUID employeeId;

    @BeforeEach
    void setup() {
        employeeId = UUID.randomUUID();

        employeeDomain = new Employee();
        employeeDomain.setId(employeeId);

        productDomain = new Product();
        productDomain.setCreatedByEmployee(employeeDomain);
        productDomain.setName("Produto Teste");

        employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeId);

        productEntity = new ProductEntity();
        productEntity.setName("Produto Teste");

        savedEntity = new ProductEntity();
        savedEntity.setName("Produto Teste");
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        // Arrange
        when(employeeEntityRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        try (MockedStatic<EmployeeMapper> employeeMapperStatic = mockStatic(EmployeeMapper.class);
             MockedStatic<ProductMapper> productMapperStatic = mockStatic(ProductMapper.class)) {

            employeeMapperStatic.when(() -> EmployeeMapper.toDomain(employeeEntity)).thenReturn(employeeDomain);
            productMapperStatic.when(() -> ProductMapper.toEntity(productDomain)).thenReturn(productEntity);
            productMapperStatic.when(() -> ProductMapper.toDomain(savedEntity)).thenReturn(productDomain);

            when(productRepository.save(productEntity)).thenReturn(savedEntity);

            // Act
            Product resultado = adapter.save(productDomain);

            // Assert
            assertNotNull(resultado);
            assertEquals("Produto Teste", resultado.getName());
            verify(employeeEntityRepository).findById(employeeId);
            verify(productRepository).save(productEntity);
        }
    }

    @Test
    void deveLancarExcecaoQuandoFuncionarioNaoEncontradoAoSalvar() {
        // Arrange
        when(employeeEntityRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act & Assert
        FastFoodException exception = assertThrows(FastFoodException.class, () -> adapter.save(productDomain));
        assertEquals("Employee not found with id: " + employeeId, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(employeeEntityRepository).findById(employeeId);
        verifyNoInteractions(productRepository);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        // Arrange
        Long productId = 1L;
        productDomain.setId(productId);

        ProductEntity existingEntity = new ProductEntity();
        existingEntity.setName("Produto Antigo");

        ProductEntity updatedEntity = new ProductEntity();
        updatedEntity.setName(productDomain.getName());

        try (MockedStatic<ProductMapper> productMapperStatic = mockStatic(ProductMapper.class)) {
            when(productRepository.findById(productId)).thenReturn(Optional.of(existingEntity));
            when(productRepository.save(existingEntity)).thenReturn(updatedEntity);
            productMapperStatic.when(() -> ProductMapper.toDomain(updatedEntity)).thenReturn(productDomain);

            // Act
            Product resultado = adapter.update(productDomain);

            // Assert
            assertNotNull(resultado);
            assertEquals(productDomain.getName(), resultado.getName());
            verify(productRepository).findById(productId);
            verify(productRepository).save(existingEntity);
        }
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontradoAoAtualizar() {
        // Arrange
        Long productId = 1L;
        productDomain.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        FastFoodException exception = assertThrows(FastFoodException.class, () -> adapter.update(productDomain));
        assertEquals("Product not found with id: " + productId, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(productRepository).findById(productId);
        verify(productRepository, never()).save(any());
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        // Arrange
        Long productId = 1L;

        // Act
        adapter.delete(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void deveListarProdutosComSucesso() {
        // Arrange
        ProductEntity entity1 = new ProductEntity();
        entity1.setName("Produto 1");

        ProductEntity entity2 = new ProductEntity();
        entity2.setName("Produto 2");

        List<ProductEntity> entities = List.of(entity1, entity2);

        try (MockedStatic<ProductMapper> productMapperStatic = mockStatic(ProductMapper.class)) {
            when(productRepository.findAll()).thenReturn(entities);
            productMapperStatic.when(() -> ProductMapper.toDomain(entity1)).thenReturn(new Product.Builder()
                    .name("Produto 1")
                    .build());
            productMapperStatic.when(() -> ProductMapper.toDomain(entity2)).thenReturn(new Product.Builder()
                    .name("Produto 2")
                    .build());

            // Act
            List<Product> resultado = adapter.findAll();

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.size());
            assertEquals("Produto 1", resultado.get(0).getName());
            assertEquals("Produto 2", resultado.get(1).getName());

            verify(productRepository).findAll();
        }
    }
}
