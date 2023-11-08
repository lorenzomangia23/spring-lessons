package com.example.springbootrestservices;

import com.example.springbootrestservices.client.RestClient;
import com.example.springbootrestservices.model.CarDto;
import com.example.springbootrestservices.model.ProductDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootRestServicesApplication {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		SpringApplication.run(SpringBootRestServicesApplication.class, args);
		new RestClient().initData(getDefaultProducts(), getDefaultCars());
	}

	private static List<ProductDto> getDefaultProducts() {
		List<ProductDto> productList = new ArrayList<>();
		productList.add(new ProductDto("Pane", 10));
		productList.add(new ProductDto("Pasta", 20));
		productList.add(new ProductDto("Formaggio", 30));
		return productList;
	}

	private static List<CarDto> getDefaultCars() {
		List<CarDto> carList = new ArrayList<>();
		carList.add(new CarDto("Audi", "A4", "40k"));
		carList.add(new CarDto("Mercedes", "C", "45k"));
		carList.add(new CarDto("Ferrari", "Roma", "200k"));
		return carList;
	}

}
