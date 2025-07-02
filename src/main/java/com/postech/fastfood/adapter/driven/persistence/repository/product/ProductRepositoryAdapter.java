package com.postech.fastfood.adapter.driven.persistence.repository.product;

import com.postech.fastfood.adapter.driven.persistence.entity.EmployeeEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.ProductEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.employee.IEmployeeEntityRepository;
import com.postech.fastfood.application.mapper.EmployeeMapper;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.Category;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final IProductRepository productRepository;
    private final IEmployeeEntityRepository employeeEntityRepository;
    private final LoggerPort logger;

    public ProductRepositoryAdapter(
            IProductRepository productRepository,
            IEmployeeEntityRepository employeeEntityRepository,
            LoggerPort logger) {
        this.productRepository = productRepository;
        this.employeeEntityRepository = employeeEntityRepository;
        this.logger = logger;
    }

    @Override
    public Product save(Product product) {
        logger.info("[Repository][Product] Iniciando criação de produto: nome={}", product.getName());

        final UUID employeeId = product.getCreatedByEmployee().getId();
        logger.debug("[Repository][Product] Buscando funcionário criador com id={}", employeeId);

        final EmployeeEntity employeeEntity = this.employeeEntityRepository.findById(employeeId)
                .orElseThrow(() -> {
                    logger.warn("[Repository][Product] Funcionário não encontrado: id={}", employeeId);

                    return new FastFoodException("Employee not found with id: " + employeeId, "Employee not found ", HttpStatus.NOT_FOUND);
                });

        product.setCreatedByEmployee(EmployeeMapper.toDomain(employeeEntity));
        final ProductEntity savedEntity = this.productRepository.save(ProductMapper.toEntity(product));

        logger.info("[Repository][Product] Produto criado com sucesso: id={}, nome={}", savedEntity.getId(), savedEntity.getName());

        return ProductMapper.toDomain(savedEntity);
    }


    public Product update(Product product) {
        final Long productId = product.getId();

        logger.info("[Repository][Product] Iniciando atualização do produto id={}", productId);

        final ProductEntity existingEntity = this.productRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.warn("[Repository][Product] Produto não encontrado: id={}", productId);

                    return new FastFoodException("Product not found with id: " + productId, "Product not found", HttpStatus.NOT_FOUND);
                });

        if (product.getName() != null) {
            existingEntity.setName(product.getName());
        }
        if (product.getCategory() != null) {
            existingEntity.setCategory(product.getCategory());
        }
        if (product.getUnitPrice() != null) {
            existingEntity.setUnitPrice(product.getUnitPrice());
        }
        if (product.getUrlImage() != null) {
            existingEntity.setUrlImage(product.getUrlImage());
        }
        if (product.getDescription() != null) {
            existingEntity.setDescription(product.getDescription());
        }

        final ProductEntity updatedEntity = this.productRepository.save(existingEntity);

        logger.info("[Repository][Product] Produto atualizado com sucesso: id={}, nome={}", updatedEntity.getId(), updatedEntity.getName());

        return ProductMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(Long idProduct) {
        logger.info("[Repository][Product] Iniciando exclusão do produto id={}", idProduct);
        final ProductEntity existingEntity = this.productRepository.findById(idProduct)
                .orElseThrow(() -> {
                    logger.warn("[Repository][Product] Produto não encontrado para exclusão: id={}", idProduct);

                    return new FastFoodException("Product not found with id: " + idProduct, "Product not found", HttpStatus.NOT_FOUND);
                });

        logger.info("[Repository][Product] Produto excluído com sucesso: id={}", idProduct);

        this.productRepository.deleteById(idProduct);
    }

    @Override
    public List<Product> findAll() {
        logger.info("[Repository][Product] Buscando todos os produtos");
        final List<Product> result = this.productRepository.findAll().stream().map(ProductMapper::toDomain).toList();
        logger.info("[Repository][Product] {} produtos encontrados", result.size());
        return result;
    }

    @Override
    public List<Product> findProductByCategory(Category category) {
        logger.info("[Repository][Product] Buscando produtos da categoria: {}", category);
        final List<Product> result = this.productRepository.findByCategory(category).stream().map(ProductMapper::toDomain).toList();
        logger.info("[Repository][Product] {} produtos encontrados na categoria {}", result.size(), category);
        return result;
    }

    @Override
    public List<Product> findAllById(List<Long> productIds) {
        logger.info("[Repository][Product] Buscando produtos pelos IDs: {}", productIds);
        final List<Product> result = this.productRepository.findAllById(productIds).stream().map(ProductMapper::toDomain).toList();

        if (result.isEmpty()) {
            logger.warn("[Repository][Product] Nenhum produto encontrado com os IDs: {}", productIds);
            throw new FastFoodException(
                    "Nenhum produto encontrado com os IDs fornecidos",
                    "Produtos não encontrados",
                    HttpStatus.NOT_FOUND
            );
        }

        logger.info("[Repository][Product] {} produtos encontrados pelos IDs", result.size());
        return result;
    }

}
