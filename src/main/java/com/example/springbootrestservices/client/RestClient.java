package com.example.springbootrestservices.client;

import com.example.springbootrestservices.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RestClient {
    String BASE_URL = "http://localhost:8080";
    public static HttpClient client = HttpClient.newBuilder().build();
    ObjectMapper mapper = new ObjectMapper();

    public List<Product> getAllProducts() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/products"))
                .headers("Accept", "application/json", "Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return List.of(mapper.readValue(response.body(), Product[].class));
    }

    public Product getProduct(long id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/products/" + id))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Product.class);
    }

    public boolean updateProduct(Product product, long id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/product/update/" + id))
                .headers("Accept", "application/json", "Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(product)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Boolean.class);
    }

    public Long addProduct(Product product) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/product/add"))
                .headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(product)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Long.class);
    }

    public boolean deleteProduct(Long id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/product/" + id))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Boolean.class);
    }

}
