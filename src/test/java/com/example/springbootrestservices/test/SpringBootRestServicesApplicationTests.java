package com.example.springbootrestservices.test;

import com.example.springbootrestservices.client.RestClient;
import com.example.springbootrestservices.model.ProductDto;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringBootRestServicesApplicationTests {
    
    Logger LOG = Logger.getLogger(SpringBootRestServicesApplicationTests.class); 
    RestClient restClient = new RestClient();

    @Test
    @Order(1)
    public void getAllProducts() throws URISyntaxException, IOException, InterruptedException {
        List<ProductDto> products = restClient.getAllProducts();
        LOG.debug("GET ALL Test - Product List: " + products);
    }

    @Test
    @Order(2)
    public void getProduct() throws URISyntaxException, IOException, InterruptedException {
        ProductDto product = restClient.getProduct(1L);
        LOG.debug("GET Test - Product: " + product);
    }

    @Test
    @Order(3)
    public void replaceProduct() throws URISyntaxException, IOException, InterruptedException {
        ProductDto product = new ProductDto(3L, "Latte", 25);
        boolean result = restClient.replaceProduct(product);
        if (result) {
            LOG.debug("REPLACE Test - Product with id " + product.getId() + " replaced");
        } else {
            LOG.debug("REPLACE Test - Product with id " + product.getId() + " not present");
        }
    }

    @Test
    @Order(4)
    public void updateProduct() throws URISyntaxException, IOException, InterruptedException {
        long productId = 3L;
        int newQuantity = 35;
        boolean result = restClient.updateProduct(3L, newQuantity);
        if (result) {
            LOG.debug("UPDATE Test - Product with id " + productId + " updated");
        } else {
            LOG.debug("UPDATE Test - Product with id " + productId + " not present");
            LOG.debug("UPDATE Test - Product created");
        }
    }

    @Test
    @Order(5)
    public void addProduct() throws URISyntaxException, IOException, InterruptedException {
        ProductDto product = new ProductDto("Gelato", 15);
        Long productId = restClient.addProduct(product);
        LOG.debug("ADD Test - Product with id " + productId + " created");
    }

    @Test
    @Order(6)
    public void deleteProduct() throws URISyntaxException, IOException, InterruptedException {
        Long productId = 3L;
        boolean isProductDeleted = restClient.deleteProduct(productId);
        if (isProductDeleted) {
            LOG.debug("DELETE Test - Product with id " + productId + " deleted");
        }
    }


}
