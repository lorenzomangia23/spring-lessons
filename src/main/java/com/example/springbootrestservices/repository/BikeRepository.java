package com.example.springbootrestservices.repository;

import com.example.springbootrestservices.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}
