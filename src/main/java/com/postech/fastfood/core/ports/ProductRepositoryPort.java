package com.postech.fastfood.core.ports;

import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.Category;
import java.util.List;

public interface ProductRepositoryPort {
    Product save(Product product);

    Product update(Product product);

    void delete(Long idProduct);

    List<Product> findAll();

    List<Product> findProductByCategory(Category category);
}
