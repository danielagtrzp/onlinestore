package com.dancode.onlinestore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMG")
    private String imageFileName;
    @Column(name = "PRICE")
    private Float priceUSD;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "DEAL")
    private Boolean deal;
    
    public Product() {
    }

    

    public Product(Long id, String name, String description, String imageFileName, Float priceUSD, String category,
            Boolean deal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageFileName = imageFileName;
        this.priceUSD = priceUSD;
        this.category = category;
        this.deal = deal;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Float getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Float priceUSD) {
        this.priceUSD = priceUSD;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getDeal() {
        return deal;
    }


    public void setDeal(Boolean deal) {
        this.deal = deal;
    }


    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", imageFileName="
                + imageFileName + ", priceUSD=" + priceUSD + ", category=" + category + ", deal=" + deal + "]";
    }

    

}
