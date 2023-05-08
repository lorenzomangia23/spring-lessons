package com.example.springbootrestservices.controller;

import com.example.springbootrestservices.model.Product;
import com.example.springbootrestservices.repository.ProductRepositoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepositoryApi productRepositoryApi;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepositoryApi.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable(value = "id") Long id) {
        return productRepositoryApi.getProduct(id);
    }

    @PatchMapping("/product/replace/")
    public boolean replaceProduct(@RequestBody Product product) {
        return productRepositoryApi.replaceProduct(product);
    }

    @PutMapping("/product/update/")
    public boolean updateProduct(@RequestBody Product product) {
        return productRepositoryApi.updateProduct(product);
    }

    @PostMapping("/product/add")
    public Long addProduct(@RequestBody Product product) {
        return productRepositoryApi.addProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return productRepositoryApi.deleteProduct(id);
    }
}
