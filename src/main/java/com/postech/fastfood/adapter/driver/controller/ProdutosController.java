package com.postech.fastfood.adapter.driver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutosController {

    @PostMapping
    public ResponseEntity<String> produtoCreate() {
        System.out.println("Teste");
        return ResponseEntity.ok().body("Teste");
    }
}
