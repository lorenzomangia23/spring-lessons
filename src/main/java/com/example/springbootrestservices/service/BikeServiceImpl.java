package com.example.springbootrestservices.service;

import com.example.springbootrestservices.entity.Bike;
import com.example.springbootrestservices.model.BikeDto;
import com.example.springbootrestservices.repository.BikeRepository;
import com.example.springbootrestservices.util.UtilCommon;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
@Service
public class BikeServiceImpl extends UtilCommon implements BikeServiceApi {
    Logger LOG = Logger.getLogger(BikeServiceImpl.class);
    @Autowired
    BikeRepository bikeRepository;

    @Override
    public List<BikeDto> getAllBikes() {
        List<Bike> bikeList = getAllBikeFromRepo();
        LOG.info("GET ALL Service - Bike List: " + bikeList);
        return mapBikeListToBikeDtoList(bikeList);
    }

    @Override
    public BikeDto getBike(long id) {
        checkTextIsPresent();
        Optional<Bike> optionalBike = bikeRepository.findById(id);
        if(optionalBike.isPresent()) {
            LOG.info("GET Service - Bike: " + optionalBike.get());
            return mapBikeToBikeDto(optionalBike.get());
        } else {
            return null;
        }
    }

//    @Override
//    public boolean replaceProduct(ProductDto newProduct) {
//        Optional<Product> optionalProduct =  productRepository.findById(newProduct.getId());
//        if(optionalProduct.isPresent()) {
//            Product product = mapProductDtoToProduct(newProduct);
//            product.setId(newProduct.getId());
//            productRepository.save(product);
//            LOG.info("PUT Service - Product List: " + getAllProductsFromRepo());
//            return true;
//        } else {
//            LOG.info("PUT Service - Product List: " + getAllProductsFromRepo());
//            return false;
//        }
//    }
//
//    @Override
//    public boolean updateProduct(Long id, int quantity) {
//        Optional<Product> optionalProduct =  productRepository.findById(id);
//        if(optionalProduct.isPresent()) {
//            optionalProduct.get().setQuantity(quantity);
//            productRepository.save((optionalProduct.get()));
//            LOG.info("PATCH Service - Product List: " + getAllProductsFromRepo());
//            return true;
//        } else {
//            LOG.info("PATCH Service - Product List: " + getAllProductsFromRepo());
//            return false;
//        }
//    }

//    @Override
//    public Long addProduct(BikeDto newBike) {
//        Bike bike = bikeRepository.save(mapBikeDtoToBike(newBike));
//        LOG.info("ADD Service - Bike: " + bike);
//        return bike.getId();
//    }

    @Override
    public void addAllBikes(List<BikeDto> bikeDtoList) {
        bikeRepository.deleteAll();
        List<Bike> bikeList = bikeDtoList
                .stream()
                .map(this::mapBikeDtoToBike)
                .toList();
        bikeRepository.saveAll(bikeList);
        LOG.info("ADD ALL Service - Bike List: " + bikeList);
    }

    @Override
    public boolean deleteBike(Long id) {
        bikeRepository.deleteById(id);
        Optional<Bike> optionalBike = bikeRepository.findById(id);
        assertTrue(optionalBike.isEmpty());
        LOG.info("DELETE Service - Bike List: " + getAllBikeFromRepo());
        return true;
    }

    private Bike mapBikeDtoToBike(BikeDto bikeDto) {
        return new Bike(bikeDto.getName(), bikeDto.getQuantity(), bikeDto.getBrand(), bikeDto.getModel(), bikeDto.getPrice());
    }

    private BikeDto mapBikeToBikeDto(Bike bike) {
        return new BikeDto(bike.getId(), bike.getName(), bike.getQuantity(), bike.getBrand(), bike.getModel(), bike.getPrice());
    }

    private List<Bike> getAllBikeFromRepo() {
        return bikeRepository.findAll();
    }

    private List<BikeDto> mapBikeListToBikeDtoList(List<Bike> bikeList) {
        return bikeList
                .stream()
                .map(this::mapBikeToBikeDto)
                .toList();
    }

    @Override
    public String getTextIdentifier() {
        return "BikeID";
    }
}
