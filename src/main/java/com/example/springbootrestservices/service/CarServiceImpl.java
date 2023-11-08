package com.example.springbootrestservices.service;

import com.example.springbootrestservices.entity.Car;
import com.example.springbootrestservices.model.CarDto;
import com.example.springbootrestservices.repository.CarRepository;
import com.example.springbootrestservices.util.UtilCommon;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl extends UtilCommon implements CarServiceApi {

    Logger LOG = Logger.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository carRepository;

    @Override
    public String getTextIdentifier() {
        return "CarID";
    }

    @Override
    public CarDto getCar(long id) {
        checkTextIsPresent();
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            LOG.info("GET Service - Car: " + optionalCar.get());
            return mapCarToCarDto(optionalCar.get());
        } else {
            return null;
        }
    }

    @Override
    public void addAllCars(List<CarDto> carDtoList) {
        carRepository.deleteAll();
        List<Car> carList = carDtoList
                .stream()
                .map(this::mapCarToCarDto)
                .toList();
        carRepository.saveAll(carList);
        LOG.info("ADD ALL Service - Car List: " + carList);
    }

    private CarDto mapCarToCarDto(Car car) {
        return new CarDto(car.getId(), car.getName(), car.getQuantity(), car.getBrand(), car.getModel(), car.getPrice());
    }

    private Car mapCarToCarDto(CarDto carDto) {
        return new Car(carDto.getName(), carDto.getQuantity(), carDto.getBrand(), carDto.getModel(), carDto.getPrice());
    }
}
