package com.dancode.onlinestore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;


import com.dancode.onlinestore.entities.Order;
import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.entities.dtos.PurchaseDto;
import com.dancode.onlinestore.entities.dtos.PurchaseDto.ProductInfo;
import com.dancode.onlinestore.exceptions.GeneralCustomApplicationException;
import com.dancode.onlinestore.repositories.OrderRepository;
import com.dancode.onlinestore.repositories.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Value("${products.service.max-number-of-items:25}")
    private Long maxNumberOfOrders;

    public void processOrders(PurchaseDto purchaseDto) throws Exception{
    
        for (ProductInfo productInfo : purchaseDto.getProducts()) {
            Order order = new Order();
            order.setFirstName(purchaseDto.getFirstName());
            order.setLastName(purchaseDto.getLastName());
            order.setEmail(purchaseDto.getEmail());
            order.setShippingAddress(purchaseDto.getShippingAddress());
            order.setCreditCard(purchaseDto.getCreditCard());

            if(productInfo.getQuantity() <= maxNumberOfOrders){
                order.setQuantity(productInfo.getQuantity());
            }else{
                throw new GeneralCustomApplicationException("You exceeded the limit of items per order");
            }

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
