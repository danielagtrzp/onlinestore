package com.dancode.onlinestore.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.dancode.onlinestore.services.ProductCategoriesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HomepageController {

    @Autowired
    private ProductCategoriesService productCategoriesService;

    @GetMapping("/categories")
    public String getAllCategoriesName() {
        return productCategoriesService.getAllCategoriesNames();
    }
    
}
