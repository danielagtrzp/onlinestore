package com.dancode.onlinestore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dancode.onlinestore.entities.Order;
import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.entities.dtos.PurchaseDto;
import com.dancode.onlinestore.entities.dtos.PurchaseDto.ProductInfo;
import com.dancode.onlinestore.repositories.OrderRepository;
import com.dancode.onlinestore.repositories.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public void processOrders(PurchaseDto purchaseDto) throws Exception {
    
        for (ProductInfo productInfo : purchaseDto.getProducts()) {
            Order order = new Order();
            order.setFirstName(purchaseDto.getFirstName());
            order.setLastName(purchaseDto.getLastName());
            order.setEmail(purchaseDto.getEmail());
            order.setShippingAddress(purchaseDto.getShippingAddress());
            order.setCreditCard(purchaseDto.getCreditCard());
            order.setQuantity(productInfo.getQuantity());

            Long id = productInfo.getProductId();
            Optional<Product> optionaProduct= productRepository.findById(id);
            if (!optionaProduct.isPresent()) {
                throw new Exception("Product not found");
            }
            
            Product product = optionaProduct.get();
            order.setProduct(product);
            orderRepository.save(order);
        
        }
    }
}
