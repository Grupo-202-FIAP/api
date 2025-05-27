package com.postech.fastfood.adapter.driven.persistence.repository;

import com.postech.fastfood.adapter.driven.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> findProductByCategory(String category);
}
