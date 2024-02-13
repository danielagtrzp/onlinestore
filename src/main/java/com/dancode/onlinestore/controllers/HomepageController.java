package com.dancode.onlinestore.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.entities.wrappers.ProductWrapper;
import com.dancode.onlinestore.services.ProductCategoriesService;
import com.dancode.onlinestore.services.ProductService;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class HomepageController {

    @Autowired
    private ProductCategoriesService productCategoriesService;

    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public String getAllCategoriesName() {
        return productCategoriesService.getAllCategoriesNames();
    }

    @GetMapping("/deals_of_the_day/{number_of_products}")
    public ProductWrapper getDealProducts(@PathVariable("number_of_products") Long number_of_products){
        ProductWrapper products = new ProductWrapper(productService.getDealProducts(number_of_products));
        return products;
    }
    
}
