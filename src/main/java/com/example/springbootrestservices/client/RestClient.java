package com.example.springbootrestservices.client;

import com.example.springbootrestservices.model.CarDto;
import com.example.springbootrestservices.model.ProductDto;
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
    String APP_CONTEXT = "/restexample";
    String VERSION = "/v1";
    public static HttpClient client = HttpClient.newBuilder().build();
    ObjectMapper mapper = new ObjectMapper();

    public void initData(List<ProductDto> productDtoList, List<CarDto> defaultCars) throws URISyntaxException, IOException, InterruptedException {
       addAllProducts(productDtoList);
       addCars(defaultCars);
    }

    private void addCars(List<CarDto> defaultCars) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/cars/all"))
                .headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(defaultCars)))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<ProductDto> getAllProducts() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/products"))
                .headers("Accept", "application/json", "Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return List.of(mapper.readValue(response.body(), ProductDto[].class));
    }

    public ProductDto getProduct(long id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/products/" + id))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), ProductDto.class);
    }

    public boolean replaceProduct(ProductDto product) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/products"))
                .headers("Accept", "application/json", "Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(product)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Boolean.class);
    }

    public boolean updateProduct(Long id, int quantity) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/products/" + id + "/" + quantity))
                .headers("Accept", "application/json", "Content-type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Boolean.class);
    }

    public Long addProduct(ProductDto product) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/products"))
                .headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(product)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Long.class);
    }

    public void addAllProducts(List<ProductDto> productDtoList) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/products/all"))
                .headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(productDtoList)))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public boolean deleteProduct(Long id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + APP_CONTEXT + VERSION + "/products/" + id))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), Boolean.class);
    }

}
