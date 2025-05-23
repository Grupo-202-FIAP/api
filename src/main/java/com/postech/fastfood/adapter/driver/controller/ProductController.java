package com.postech.fastfood.adapter.driver.controller;

import java.util.List;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductUpdateRequest;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.usecase.product.CreateProductUseCase;
import com.postech.fastfood.core.usecase.product.DeleteProductUseCase;
import com.postech.fastfood.core.usecase.product.ListProductsUseCase;
import com.postech.fastfood.core.usecase.product.UpdateProductUseCase;
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
@RequestMapping("/api/v1/product")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, DeleteProductUseCase deleteProductUseCase, ListProductsUseCase listProductsUseCase, UpdateProductUseCase updateProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product domain = ProductMapper.toDomain(productRequest);
        Product productCreated = createProductUseCase.execute(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(listProductsUseCase.execute());
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductUpdateRequest productRequest, @RequestParam Long id) {
        Product domain = ProductMapper.toDomain(productRequest);
        Product execute = this.updateProductUseCase.execute(domain, id);
        return ResponseEntity.status(HttpStatus.OK).body(execute);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        this.deleteProductUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}
