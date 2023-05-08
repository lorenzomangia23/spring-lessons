package com.example.springbootrestservices;

import com.example.springbootrestservices.client.RestClient;
import com.example.springbootrestservices.model.Product;
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
    RestClient restClient = new RestClient();

    @Test
    @Order(1)
    public void getAllProductsRestClient() throws URISyntaxException, IOException, InterruptedException {
        List<Product> products = restClient.getAllProducts();
        System.out.println("Products: " + products);
    }

    @Test
    @Order(2)
    public void getProduct() throws URISyntaxException, IOException, InterruptedException {
        Product product = restClient.getProduct(1L);
        System.out.println("Product: " + product);
    }

    @Test
    @Order(3)
    public void replaceProduct() throws URISyntaxException, IOException, InterruptedException {
        Product product = new Product(3L, "Fanta");
        boolean result = restClient.replaceProduct(product);
        if (result) {
            System.out.println("Product with id " + product.getId() + " replaced");
        } else {
            System.out.println("Product with id " + product.getId() + " not present");
        }
    }

    @Test
    @Order(4)
    public void updateProduct() throws URISyntaxException, IOException, InterruptedException {
        Product product = new Product(3L, "Wine");
        boolean result = restClient.updateProduct(product);
        if (result) {
            System.out.println("Product with id " + product.getId() + " updated");
        } else {
            System.out.println("Product with id " + product.getId() + " not present");
            System.out.println("New product created");
        }
    }

    @Test
    @Order(5)
    public void addProduct() throws URISyntaxException, IOException, InterruptedException {
        Product product = new Product("Cola");
        Long productId = restClient.addProduct(product);
        System.out.println("New product with id " + productId + " created");
    }

    @Test
    @Order(6)
    public void deleteProduct() throws URISyntaxException, IOException, InterruptedException {
        Long productId = 3L;
        boolean isProductDeleted = restClient.deleteProduct(productId);
        if (isProductDeleted) {
            System.out.println("Product with id " + productId + " deleted");
        }
    }


}
