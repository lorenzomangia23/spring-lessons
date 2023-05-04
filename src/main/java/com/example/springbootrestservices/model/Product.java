package com.example.springbootrestservices.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    Long id;
    String name;

    public Product(String name) {
        this.name = name;
    }
    @JsonCreator
    public Product(@JsonProperty("id") Long id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return "Id:" + this.id + ", Name: " + this.name;
    }
}