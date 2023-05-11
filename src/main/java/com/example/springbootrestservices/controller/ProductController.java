package com.example.springbootrestservices.controller;

import com.example.springbootrestservices.model.ProductDto;
import com.example.springbootrestservices.service.ProductServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductServiceApi productServiceApi;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productServiceApi.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable(value = "id") Long id) {
        return productServiceApi.getProduct(id);
    }

    @PutMapping("/products")
    public boolean replaceProduct(@RequestBody ProductDto product) {
        return productServiceApi.replaceProduct(product);
    }

    @PatchMapping("/products/{id}/{quantity}")
    public boolean updateProduct(@PathVariable(value = "id") Long id, @PathVariable(value = "quantity") int quantity) {
        return productServiceApi.updateProduct(id, quantity);
    }

    @PostMapping("/products")
    public Long addProduct(@RequestBody ProductDto product) {
        return productServiceApi.addProduct(product);
    }

    @PostMapping("/products/all")
    public void addAllProducts(@RequestBody List<ProductDto> productDtoList) {
        productServiceApi.addAllProducts(productDtoList);
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return productServiceApi.deleteProduct(id);
    }
}
