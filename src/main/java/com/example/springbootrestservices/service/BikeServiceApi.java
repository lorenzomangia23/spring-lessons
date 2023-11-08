package com.example.springbootrestservices.service;

import com.example.springbootrestservices.model.BikeDto;

import java.util.List;

public interface BikeServiceApi {

    List<BikeDto> getAllBikes();

    BikeDto getBike(long id);

//    boolean replaceProduct(ProductDto product);
//
//    boolean updateProduct(Long id, int quantity);
//
//    Long addProduct(ProductDto product);

    void addAllBikes(List<BikeDto> bikeDtoList);

    boolean deleteBike(Long id);
}