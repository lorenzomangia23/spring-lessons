package com.example.springbootrestservices.repository;

import com.example.springbootrestservices.model.Product;

import java.util.List;

public interface ProductRepositoryApi {

    List<Product> getAllProducts();

    Product getProduct(long id);

    boolean updateProduct(Product product, Long id);

    Long addProduct(Product product);

    boolean deleteProduct(Long id);
}