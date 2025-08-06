package com.postech.fastfood.adapter.driven.persistence.repository.product;

import com.postech.fastfood.adapter.driven.persistence.entity.EmployeeEntity;
import com.postech.fastfood.adapter.driven.persistence.entity.ProductEntity;
import com.postech.fastfood.adapter.driven.persistence.repository.employee.IEmployeeEntityRepository;
import com.postech.fastfood.application.mapper.EmployeeMapper;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.Category;
import com.postech.fastfood.core.exception.FastFoodException;
import com.postech.fastfood.core.ports.ProductRepositoryPort;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final IProductRepository productRepository;
    private final IEmployeeEntityRepository employeeEntityRepository;

    public ProductRepositoryAdapter(IProductRepository productRepository, IEmployeeEntityRepository employeeEntityRepository) {
        this.productRepository = productRepository;
        this.employeeEntityRepository = employeeEntityRepository;
    }

    @Override
    public Product save(Product product) {
        final UUID employeeId = product.getCreatedByEmployee().getId();

        final EmployeeEntity employeeEntity = this.employeeEntityRepository.findById(employeeId)
                .orElseThrow(() -> new FastFoodException("Employee not found with id: " + employeeId, "Employee not found ", HttpStatus.NOT_FOUND));

        product.setCreatedByEmployee(EmployeeMapper.toDomain(employeeEntity));

        final ProductEntity savedEntity = this.productRepository.save(ProductMapper.toEntity(product));

        return ProductMapper.toDomain(savedEntity);
    }


    public Product update(Product product) {
        final Long productId = product.getId();

        final ProductEntity existingEntity = this.productRepository.findById(productId)
                .orElseThrow(() -> new FastFoodException("Product not found with id: " + productId, "Product not found", HttpStatus.NOT_FOUND));

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

        return ProductMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(Long idProduct) {
        final ProductEntity existingEntity = this.productRepository.findById(idProduct)
                .orElseThrow(() -> new FastFoodException("Product not found with id: " + idProduct, "Product not found", HttpStatus.NOT_FOUND));
        this.productRepository.deleteById(idProduct);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll().stream().map(ProductMapper::toDomain).toList();
    }

    @Override
    public List<Product> findProductByCategory(Category category) {
        return this.productRepository.findByCategory(category).stream().map(ProductMapper::toDomain).toList();
    }

    @Override
    public List<Product> findAllById(List<Long> productIds) {
        return this.productRepository.findAllById(productIds).stream().map(ProductMapper::toDomain).toList();
    }

}
