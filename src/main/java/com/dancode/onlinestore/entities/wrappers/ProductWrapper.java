package com.dancode.onlinestore.entities.wrappers;

import java.util.List;

import com.dancode.onlinestore.entities.Product;

public class ProductWrapper {

    private List<Product> products;

    

    public ProductWrapper(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    

}
