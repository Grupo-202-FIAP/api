package com.postech.fastfood.application.gateways;

import com.postech.fastfood.domain.Product;
import com.postech.fastfood.domain.enums.Category;
import java.util.List;

public interface ProductRepositoryPort {
    Product save(Product product);

    Product update(Product product);

    void delete(Long idProduct);

    List<Product> findAll();

    List<Product> findProductByCategory(Category category);

    List<Product> findAllById(List<Long> productIds);
}
