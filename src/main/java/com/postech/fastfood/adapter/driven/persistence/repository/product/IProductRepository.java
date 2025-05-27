package com.postech.fastfood.adapter.driven.persistence.repository.product;

import com.postech.fastfood.adapter.driven.persistence.entity.ProductEntity;
import com.postech.fastfood.core.domain.enums.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(Category category);
}
