package com.dancode.onlinestore.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.dancode.onlinestore.entities.dtos.ProductDto;
import com.dancode.onlinestore.services.ProductCategoryService;
import com.dancode.onlinestore.services.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class HomepageController {

    @Autowired
    private ProductCategoryService productCategoriesService;

    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public String getAllCategoriesName() {
        return productCategoriesService.getAllCategoriesNames();
    }

    @GetMapping("/deals_of_the_day/{number_of_products}")
    public ProductDto getDealProducts(@PathVariable("number_of_products") Long number_of_products){
        ProductDto products = new ProductDto(productService.getDealProducts(number_of_products));
        return products;
    }

    @GetMapping("/products/categories")
    public ProductDto getProductsByCategory(@RequestParam String category){
        ProductDto products = new ProductDto(productService.getProductsByCategory(category));
        return products;
    }

    @GetMapping("/products")
    public ProductDto getAllProducts(){
        ProductDto products = new ProductDto(productService.getAllProducts());
        return products;
    }
    
}
