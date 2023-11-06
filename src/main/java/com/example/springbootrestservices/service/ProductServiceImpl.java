package com.example.springbootrestservices.service;

import com.example.springbootrestservices.entity.Product;
import com.example.springbootrestservices.model.ProductDto;
import com.example.springbootrestservices.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import static org.junit.Assert.*;
@Service
public class ProductServiceImpl implements ProductServiceApi {
    Logger LOG = Logger.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = getAllProductsFromRepo();
        LOG.info("GET ALL Service - Product List: " + productList);
        return mapProductListToProductDtoList(productList);
    }

    @Override
    public ProductDto getProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            LOG.info("GET Service - Product: " + optionalProduct.get());
            return mapProductToProductDto(optionalProduct.get());
        } else {
            return null;
        }
    }

    @Override
    public boolean replaceProduct(ProductDto newProduct) {
        Optional<Product> optionalProduct =  productRepository.findById(newProduct.getId());
        if(optionalProduct.isPresent()) {
            Product product = mapProductDtoToProduct(newProduct);
            product.setId(newProduct.getId());
            productRepository.save(product);
            LOG.info("PUT Service - Product List: " + getAllProductsFromRepo());
            return true;
        } else {
            LOG.info("PUT Service - Product List: " + getAllProductsFromRepo());
            return false;
        }
    }

    @Override
    public boolean updateProduct(Long id, int quantity) {
        Optional<Product> optionalProduct =  productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            optionalProduct.get().setQuantity(quantity);
            productRepository.save((optionalProduct.get()));
            LOG.info("PATCH Service - Product List: " + getAllProductsFromRepo());
            return true;
        } else {
            LOG.info("PATCH Service - Product List: " + getAllProductsFromRepo());
            return false;
        }
    }

    @Override
    public Long addProduct(ProductDto newProduct) {
        Product product = productRepository.save(mapProductDtoToProduct(newProduct));
        LOG.info("ADD Service - Product: " + product);
        return product.getId();
    }

    @Override
    public void addAllProducts(List<ProductDto> productDtoList) {
        productRepository.deleteAll();
        List<Product> productList = productDtoList
                .stream()
                .map(this::mapProductDtoToProduct)
                .toList();
        productRepository.saveAll(productList);
        LOG.info("ADD ALL Service - Product List: " + productList);
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        assertTrue(optionalProduct.isEmpty());
        LOG.info("DELETE Service - Product List: " + getAllProductsFromRepo());
        return true;
    }

    private Product mapProductDtoToProduct(ProductDto productDto) {
        return new Product(productDto.getName(), productDto.getQuantity());
    }

    private ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getQuantity());
    }

    private List<Product> getAllProductsFromRepo() {
        return productRepository.findAll();
    }

    private List<ProductDto> mapProductListToProductDtoList(List<Product> productList) {
        return productList
                .stream()
                .map(this::mapProductToProductDto)
                .toList();
    }
}
