package com.dancode.onlinestore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.repositories.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getDealProducts(Long number_of_products){

        int number =Long.valueOf(number_of_products).intValue();
        var products = productRepository.findByDeal(true,PageRequest.of(0, number));
        return products;
    }

    public List<Product> getProductsByCategory(String category){
        return productRepository.findByCategory(category);
    }
}
