package com.dancode.onlinestore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    List<Product> products;

    @BeforeEach
    void setUp() {
        products = List.of(
                new Product(),
                new Product(),
                new Product());
    }

    @Test
    void testGetAllProducts() {
        Long number = 2L;

        given(productRepository.findByDeal(anyBoolean(), any())).willReturn(products);

        var result = productService.getDealProducts(number);

        assertEquals(products, result);
    }

    @Test
    void testGetDealProducts() {

        given(productRepository.findByCategory(anyString())).willReturn(products);

        var result = productService.getProductsByCategory("toys");

        assertEquals(products, result);
    }

    @Test
    void testGetProductsByCategory() {

        given(productRepository.findAll()).willReturn(products);

        var result = productService.getAllProducts();

        assertEquals(products, result);
    }
}
