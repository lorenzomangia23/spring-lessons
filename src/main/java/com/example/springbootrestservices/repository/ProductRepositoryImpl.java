package com.example.springbootrestservices.repository;

import com.example.springbootrestservices.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.junit.Assert.*;
@Repository
public class ProductRepositoryImpl implements ProductRepositoryApi {

    private final List<Product> productList;

    public ProductRepositoryImpl() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Water"));
        productList.add(new Product(2L, "Beer"));
        productList.add(new Product(3L, "Wine"));
    }
    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProduct(long id) {
        List<Product> filteredProducts = productList
                .stream()
                .filter(product -> product.getId() == id)
                .toList();
        assertEquals(1, filteredProducts.size());
        return filteredProducts.get(0);
    }

    @Override
    public boolean replaceProduct(Product newProduct) {
        Optional<Product> filteredProduct = productList
                .stream()
                .filter(product -> Objects.equals(product.getId(), newProduct.getId()))
                .findFirst();
        if (filteredProduct.isPresent()) {
            int index = productList.indexOf(filteredProduct.get());
            productList.set(index, newProduct);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product newProduct) {
        Optional<Product> filteredProduct = productList
                .stream()
                .filter(product -> Objects.equals(product.getId(), newProduct.getId()))
                .findFirst();
        if (filteredProduct.isPresent()) {
            filteredProduct.get().setName(newProduct.getName());
            return true;
        } else {
            productList.add(newProduct);
            return false;
        }
    }

    @Override
    public Long addProduct(Product newProduct) {
        newProduct.setId(getLastElementId(productList) + 1L);
        productList.add(newProduct);
        return newProduct.getId();
    }

    @Override
    public boolean deleteProduct(Long id) {
        for (Product product : productList) {
            if (Objects.equals(product.getId(), id)) {
                productList.remove(product);
                System.out.println("Product List after deletion: " + productList);
                return true;
            }
        }
        System.out.println("Product with id " + id + " not deleted");
        return false;
    }

    private Long getLastElementId(List<Product> productList) {
       return productList.get(productList.size() - 1).getId();
    }
}
