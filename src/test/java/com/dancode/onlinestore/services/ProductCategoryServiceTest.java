package com.dancode.onlinestore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancode.onlinestore.entities.ProductCategory;
import com.dancode.onlinestore.repositories.ProductCategoryRepository;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceTest {

    @Mock
    ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    ProductCategoryService productCategoryService;

    @Test
    void testGetAllCategoriesNames() {
        
        //given
        List<ProductCategory> categories = List.of(
            new ProductCategory(1L, "toys"),
            new ProductCategory(1L, "electhonics"),
            new ProductCategory(1L, "home")
        );

        given(productCategoryRepository.findAll()).willReturn(categories);


        //when
        String result = productCategoryService.getAllCategoriesNames();

        //then
        assertEquals("toys, electhonics, home", result);
    }
}
