package com.dancode.onlinestore.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.entities.dtos.ProductDto;
import com.dancode.onlinestore.exceptions.CustomErrorDetails;
import com.dancode.onlinestore.exceptions.GeneralCustomApplicationException;
import com.dancode.onlinestore.services.ProductCategoryService;
import com.dancode.onlinestore.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class HomepageController {

    @Autowired
    private ProductCategoryService productCategoriesService;

    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public String getAllCategoriesName() throws GeneralCustomApplicationException {
        
        String allCategoriesNames = productCategoriesService.getAllCategoriesNames();
        if (allCategoriesNames.isEmpty()) {
            throw new GeneralCustomApplicationException("any categories name is present");
        }
        return allCategoriesNames;
    }

    @GetMapping("/deals_of_the_day/{number_of_products}")
    public ProductDto getDealProducts(@PathVariable("number_of_products") Long number_of_products) throws GeneralCustomApplicationException {
        List<Product> dealProducts = productService.getDealProducts(number_of_products);
        if (dealProducts.isEmpty()) {
            throw new GeneralCustomApplicationException("not deals products");
        }
        ProductDto products = new ProductDto(dealProducts);
        return products;
    }

    @GetMapping("/products/category")
    public ProductDto getProductsByCategory(@RequestParam String category) throws GeneralCustomApplicationException{
        List<Product> productsByCategory = productService.getProductsByCategory(category);
        if (productsByCategory.isEmpty()) {
            throw new GeneralCustomApplicationException("not products in this category");
        }
        ProductDto products = new ProductDto(productsByCategory);
        return products;

    }

    @GetMapping("/products")
    public ProductDto getAllProducts() throws GeneralCustomApplicationException{
        List<Product> allProducts = productService.getAllProducts();
        if (allProducts.isEmpty()) {
            throw new GeneralCustomApplicationException("not products avaliables");
        }
        ProductDto products = new ProductDto(allProducts);
        return products;
    }
    
    
}
