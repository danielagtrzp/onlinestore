package com.dancode.onlinestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.dancode.onlinestore.entities.dtos.PurchaseDto;
import com.dancode.onlinestore.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CheckoutpageController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public String createOrder(@RequestBody PurchaseDto purchaseDto) {
        try {
            return orderService.processOrders(purchaseDto);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    

}
