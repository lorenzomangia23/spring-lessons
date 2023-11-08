package com.example.springbootrestservices.repository;

import com.example.springbootrestservices.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
