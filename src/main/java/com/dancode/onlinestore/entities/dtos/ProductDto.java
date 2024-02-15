package com.dancode.onlinestore.entities.dtos;

import java.util.List;

import com.dancode.onlinestore.entities.Product;

public class ProductDto {

    private List<Product> products;

    

    public ProductDto(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    

}
