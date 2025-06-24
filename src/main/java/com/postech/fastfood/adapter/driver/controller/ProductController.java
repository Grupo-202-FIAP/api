package com.postech.fastfood.adapter.driver.controller;

import com.postech.fastfood.adapter.driver.controller.dto.request.ProductRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.ProductUpdateRequest;
import com.postech.fastfood.adapter.driver.controller.dto.response.ProductsResponse;
import com.postech.fastfood.application.mapper.ProductMapper;
import com.postech.fastfood.core.domain.Product;
import com.postech.fastfood.core.domain.enums.Category;
import com.postech.fastfood.core.ports.LoggerPort;
import com.postech.fastfood.core.usecase.product.CreateProductUseCase;
import com.postech.fastfood.core.usecase.product.DeleteProductUseCase;
import com.postech.fastfood.core.usecase.product.ListProductByCategoryUseCase;
import com.postech.fastfood.core.usecase.product.ListProductsUseCase;
import com.postech.fastfood.core.usecase.product.UpdateProductUseCase;
import jakarta.validation.Valid;
import java.util.List;
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
    private final LoggerPort logger;

    public ProductController(CreateProductUseCase createProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             ListProductsUseCase listProductsUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             ListProductByCategoryUseCase listProductByCategoryUseCase,
                             LoggerPort logger) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.listProductsUseCase = listProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.listProductByCategoryUseCase = listProductByCategoryUseCase;
        this.logger = logger;
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductsResponse>> listProductByCategory(@RequestParam Category category) {
        logger.info("[Product] Buscando produtos da categoria: {}", category);
        final List<Product> products = listProductByCategoryUseCase.execute(category);
        final List<ProductsResponse> response = products.stream()
                .map(ProductMapper::toResponse)
                .toList();
        logger.info("[Product] {} produtos encontrados na categoria {}", products.size(), category);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProductsResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        logger.info("[Product] Iniciando criação de produto: nome={}", productRequest.name());
        final Product domain = ProductMapper.toDomain(productRequest);
        final Product productCreated = createProductUseCase.execute(domain);
        final ProductsResponse productCreatedResponse = ProductMapper.toResponse(productCreated);
        logger.info("[Product] Produto criado com sucesso: id={}, nome={}", productCreated.getId(), productCreated.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreatedResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductsResponse>> listProduct() {
        logger.info("[Product] Buscando todos os produtos");
        final List<Product> products = listProductsUseCase.execute();
        final List<ProductsResponse> response = products.stream()
                .map(ProductMapper::toResponse)
                .toList();
        logger.info("[Product] {} produtos encontrados", products.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<ProductsResponse> updateProduct(@RequestBody @Valid ProductUpdateRequest productRequest, @RequestParam Long id) {
        logger.info("[Product] Iniciando atualização do produto: id={}", id);
        final Product domain = ProductMapper.toDomain(productRequest);
        final Product execute = this.updateProductUseCase.execute(domain, id);
        final ProductsResponse response = ProductMapper.toResponse(execute);
        logger.info("[Product] Produto atualizado com sucesso: id={}, novo nome={}", execute.getId(), execute.getName());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        logger.info("[Product] Iniciando exclusão do produto com id={}", id);
        this.deleteProductUseCase.execute(id);
        logger.info("[Product] Produto excluído com sucesso: id={}", id);
        return ResponseEntity.ok().build();
    }

}
