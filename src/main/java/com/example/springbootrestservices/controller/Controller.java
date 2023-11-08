package com.example.springbootrestservices.controller;

import com.example.springbootrestservices.model.BikeDto;
import com.example.springbootrestservices.model.CarDto;
import com.example.springbootrestservices.service.CarServiceApi;
import com.example.springbootrestservices.service.BikeServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restexample")
public class Controller {
    @Autowired
    BikeServiceApi bikeServiceApi;

    @Autowired
    CarServiceApi carServiceApi;

    @GetMapping("${bikes.base.uri}")
    public List<BikeDto> getAllBikes() {
        return bikeServiceApi.getAllBikes();
    }

    @GetMapping("${bikes.uri.id}")
    public BikeDto getBike(@PathVariable Long id) {
        return bikeServiceApi.getBike(id);
    }

    @GetMapping("${cars.uri.id}")
    public CarDto getCar(@PathVariable Long id) {
        return carServiceApi.getCar(id);
    }

//    @PutMapping("${bikes.base.uri}")
//    public boolean replaceProduct(@RequestBody ProductDto product) {
//        return bikeServiceApi.replaceProduct(product);
//    }
//
//    @PatchMapping("${bikes.uri.update}")
//    public boolean updateProduct(@PathVariable Long id, @PathVariable int quantity) {
//        return bikeServiceApi.updateProduct(id, quantity);
//    }

//    @PostMapping("${bikes.base.uri}")
//    public Long addProduct(@RequestBody ProductDto product) {
//        return bikeServiceApi.addProduct(product);
//    }

    @PostMapping("${bikes.uri.add.all}")
    public void addAllBikes(@RequestBody List<BikeDto> bikeDtoList) {
        bikeServiceApi.addAllBikes(bikeDtoList);
    }

    @PostMapping("${cars.uri.add.all}")
    public void addAllCars(@RequestBody List<CarDto> carDtoList) {
        carServiceApi.addAllCars(carDtoList);
    }

    @DeleteMapping("${bikes.uri.id}")
    public boolean deleteBike(@PathVariable Long id) {
        return bikeServiceApi.deleteBike(id);
    }
}
