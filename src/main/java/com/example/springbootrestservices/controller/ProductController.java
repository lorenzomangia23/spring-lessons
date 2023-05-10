package com.example.springbootrestservices.controller;

import com.example.springbootrestservices.model.Product;
import com.example.springbootrestservices.service.ProductServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductServiceApi productServiceApi;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productServiceApi.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable(value = "id") Long id) {
        return productServiceApi.getProduct(id);
    }

    @PatchMapping("/product/replace/")
    public boolean replaceProduct(@RequestBody Product product) {
        return productServiceApi.replaceProduct(product);
    }

    @PutMapping("/product/update/")
    public boolean updateProduct(@RequestBody Product product) {
        return productServiceApi.updateProduct(product);
    }

    @PostMapping("/product/add")
    public Long addProduct(@RequestBody Product product) {
        return productServiceApi.addProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return productServiceApi.deleteProduct(id);
    }
}
