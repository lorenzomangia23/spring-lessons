package com.example.springbootrestservices.repository;

import com.example.springbootrestservices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
