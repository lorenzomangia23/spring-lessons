package com.example.springbootrestservices.service;

import com.example.springbootrestservices.model.Product;

import java.util.List;

public interface ProductServiceApi {

    List<Product> getAllProducts();

    Product getProduct(long id);

    boolean replaceProduct(Product product);

    boolean updateProduct(Product product);

    Long addProduct(Product product);

    boolean deleteProduct(Long id);
}