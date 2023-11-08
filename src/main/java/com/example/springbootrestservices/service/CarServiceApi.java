package com.example.springbootrestservices.service;

import com.example.springbootrestservices.model.CarDto;

import java.util.List;

public interface CarServiceApi {

    CarDto getCar(long id);

    void addAllCars(List<CarDto> carDtoList);
}
