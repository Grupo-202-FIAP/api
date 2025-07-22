package com.postech.fastfood.infrastructure.repository.product;

import com.postech.fastfood.domain.enums.Category;
import com.postech.fastfood.infrastructure.repository.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(Category category);
}
