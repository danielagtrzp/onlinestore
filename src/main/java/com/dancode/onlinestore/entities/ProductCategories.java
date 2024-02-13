package com.dancode.onlinestore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT_CATEGORIES")
public class ProductCategories {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public ProductCategories() {
    }

    public ProductCategories(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductCategories [id=" + id + ", name=" + name + "]";
    }
    
}
