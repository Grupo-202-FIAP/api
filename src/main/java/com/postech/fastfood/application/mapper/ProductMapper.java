package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.ProductEntity;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductUpdateRequest;
import com.postech.fastfood.core.domain.Employee;
import com.postech.fastfood.core.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product toDomain(ProductEntity product) {
        return new Product.Builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .urlImage(product.getUrlImage())
                .createdByEmployee(EmployeeMapper.toDomain(product.getCreatedByEmployee()))
                .updatedAt(product.getUpdatedAt())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public static ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .urlImage(product.getUrlImage())
                .createdByEmployee(EmployeeMapper.toEntity(product.getCreatedByEmployee()))
                .updatedAt(product.getUpdatedAt())
                .createdAt(product.getCreatedAt())
                .build();

    }

    public static Product toDomain(ProductRequest product) {

        final Employee employee = new Employee.Builder()
                .id(product.employee_id())
                .build();

        return new Product.Builder()
                .name(product.name())
                .category(product.category())
                .description(product.description())
                .unitPrice(product.unitPrice())
                .urlImage(product.urlImage())
                .createdByEmployee(employee)
                .build();
    }

    public static Product toDomain(ProductUpdateRequest product) {
        return new Product.Builder()
                .name(product.name())
                .category(product.category())
                .description(product.description())
                .unitPrice(product.unitPrice())
                .urlImage(product.urlImage())
                .build();
    }

}
