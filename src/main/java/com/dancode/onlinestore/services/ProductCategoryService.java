package com.dancode.onlinestore.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dancode.onlinestore.repositories.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoriesRepository;

    public String getAllCategoriesNames(){
        String categoriesNames = productCategoriesRepository
                                .findAll()
                                .stream()
                                .map(e->e.getName())
                                .collect(Collectors.toList())
                                .toString();
        return categoriesNames.substring(1, categoriesNames.length()-1);
    }

}
