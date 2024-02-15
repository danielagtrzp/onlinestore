package com.dancode.onlinestore.entities.dtos;

import java.util.List;

public class PurchaseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String creditCard;
    private List<ProductInfo> products;

    public static class ProductInfo {
        
        private Long productId;
        private Long quantity;

        public Long getProductId() {
            return productId;
        }
        public void setProductId(Long productId) {
            this.productId = productId;
        }
        public Long getQuantity() {
            return quantity;
        }
        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }
        
    }

    public PurchaseDto() {
    }

    public PurchaseDto(String firstName, String lastName, String email, String shippingAddress, String creditCard,
            List<ProductInfo> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.creditCard = creditCard;
        this.products = products;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "PurchaseModel [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", shippingAddress=" + shippingAddress + ", creditCard=" + creditCard + ", products="
                + products + "]";
    }

    
    
}
