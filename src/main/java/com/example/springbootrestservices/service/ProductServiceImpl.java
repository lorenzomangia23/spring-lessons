package com.example.springbootrestservices.service;

import com.example.springbootrestservices.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.junit.Assert.*;
@Service
public class ProductServiceImpl implements ProductServiceApi {

    private final List<Product> productList;

    public ProductServiceImpl() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Pane"));
        productList.add(new Product(2L, "Pasta"));
        productList.add(new Product(3L, "Formaggio"));
    }
    @Override
    public List<Product> getAllProducts() {
        System.out.println("GET ALL Service - Product List: " + productList);
        return productList;
    }

    @Override
    public Product getProduct(long id) {
        List<Product> filteredProducts = productList
                .stream()
                .filter(product -> product.getId() == id)
                .toList();
        assertEquals(1, filteredProducts.size());
        System.out.println("GET Service - Product: " + filteredProducts.get(0));
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
            System.out.println("PATCH Service - Product List: " + productList);
            return true;
        } else {
            System.out.println("PATCH Service - Product List: " + productList);
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
            System.out.println("UPDATE Service - Product List: " + productList);
            return true;
        } else {
            productList.add(newProduct);
            System.out.println("UPDATE Service - Product List: " + productList);
            return false;
        }
    }

    @Override
    public Long addProduct(Product newProduct) {
        newProduct.setId(getLastElementId(productList) + 1L);
        productList.add(newProduct);
        System.out.println("ADD Service - Product List: " + productList);
        return newProduct.getId();
    }

    @Override
    public boolean deleteProduct(Long id) {
        for (Product product : productList) {
            if (Objects.equals(product.getId(), id)) {
                productList.remove(product);
                System.out.println("DELETE Service - Product List: " + productList);
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
