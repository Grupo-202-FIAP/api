package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.core.domain.enums.Category;
import com.postech.fastfood.core.usecase.product.CreateProductUseCase;
import com.postech.fastfood.core.usecase.product.DeleteProductUseCase;
import com.postech.fastfood.core.usecase.product.ListProductByCategoryUseCase;
import com.postech.fastfood.core.usecase.product.ListProductsUseCase;
import com.postech.fastfood.core.usecase.product.UpdateProductUseCase;
import java.util.List;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductUpdateRequest;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ListProductByCategoryUseCase listProductByCategoryUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             ListProductsUseCase listProductsUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             ListProductByCategoryUseCase listProductByCategoryUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.listProductByCategoryUseCase = listProductByCategoryUseCase;
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> listProductByCategory(@RequestParam Category category) {
        return ResponseEntity.status(HttpStatus.OK).body(listProductByCategoryUseCase.execute(category));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        final Product domain = ProductMapper.toDomain(productRequest);
        final Product productCreated = createProductUseCase.execute(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(listProductsUseCase.execute());
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductUpdateRequest productRequest, @RequestParam Long id) {
        final Product domain = ProductMapper.toDomain(productRequest);
        final Product execute = this.updateProductUseCase.execute(domain, id);
        return ResponseEntity.status(HttpStatus.OK).body(execute);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        this.deleteProductUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}
