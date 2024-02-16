package com.dancode.onlinestore.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.dancode.onlinestore.entities.dtos.PurchaseDto;
import com.dancode.onlinestore.services.CreditCardValidationService;
import com.dancode.onlinestore.services.OrderService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CheckoutpageController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CreditCardValidationService cardValidationService;

    @PostMapping("/checkout")
    public ResponseEntity<List<String>> createOrder(@Valid @RequestBody PurchaseDto purchaseDto, BindingResult result) {
        
        if (result.hasErrors()) {
            var messageErrors = result.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
        
            if (result.hasFieldErrors("creditCard")) {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(messageErrors);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageErrors);
            }
        }

        try {
            cardValidationService.validate(purchaseDto.getCreditCard());
            orderService.processOrders(purchaseDto);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(e.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(List.of("success"));
       
    }

}
