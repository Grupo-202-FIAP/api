package com.postech.fastfood.core.ports;

import java.util.List;
import com.postech.fastfood.core.domain.Product;

public interface ProductRepositoryPort {
    Product save(Product product);

    Product update(Product product);

    void delete(Long idProduct);

    List<Product> findAll();
}
