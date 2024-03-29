package com.example.springbootrestservices.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BikeDto extends ProductDto{

    private String brand;
    private String model;

    private String price;


    @JsonCreator
    public BikeDto(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("quantity") int quantity, @JsonProperty("brand") String brand, @JsonProperty("model") String model, @JsonProperty("price") String price) {
        super(id, name, quantity);
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BikeDto{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
