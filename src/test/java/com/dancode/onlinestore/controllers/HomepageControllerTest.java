package com.dancode.onlinestore.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.exceptions.GeneralCustomApplicationException;
import com.dancode.onlinestore.exceptions.GlobalExceptionHandler;
import com.dancode.onlinestore.services.ProductCategoryService;
import com.dancode.onlinestore.services.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HomepageControllerTest {

    @Mock
    ProductCategoryService productCategoryService;

    @Mock
    ProductService productService;

    @InjectMocks
    HomepageController homepageController;


    @BeforeEach
    void setUp() {
        //manual implementation of MockMVC to avoid load al the web Context of Spring
        mockMvc = MockMvcBuilders.standaloneSetup(homepageController)
                                    .setControllerAdvice(GlobalExceptionHandler.class)
                                    .build();
    }

    MockMvc mockMvc;


    //getAllCategoriesName()
    @Test
    void testGetAllCategoriesName() throws Exception{
        String categoriesNames = "toys, electronics, art, music, apparel, jewelry";
        /*
            //--> direct aproach without simulates HTTP request and response
            //given
            given(productCategoryService.getAllCategoriesNames()).willReturn(categoriesNames);

            //when
            String result = homepageController.getAllCategoriesName();

            //then
            assertEquals(categoriesNames, result);
        */


        //--> using mockMVC(manual) to simulate HTTP request and response

        //given
        given(productCategoryService.getAllCategoriesNames()).willReturn(categoriesNames);

        //when
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(categoriesNames));

    }

    @Test
    void testGetAllCategoriesNameIsEmpty() throws Exception{

        //given
        given(productCategoryService.getAllCategoriesNames())
        .willReturn("");

        //when
        mockMvc.perform(get("/categories"))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> {
                    if (result.getResolvedException() instanceof GeneralCustomApplicationException) {
                        assertEquals("any categories name is present", result.getResolvedException().getMessage());
                    } else {
                        throw new AssertionError("Se esperaba una GeneralCustomApplicationException");
                    }
                });
    }


    //getDealProducts()
    @Test
    void testGetDealProducts() throws Exception {
        //given
        List<Product> products = List.of(
            new Product(Long.valueOf(10l),"quest","to play","virtual reality glasses",Float.valueOf(10f),"toys",Boolean.valueOf(true)),
            new Product(Long.valueOf(11l),"some","to play","virtual reality glasses",Float.valueOf(10f),"toys",Boolean.valueOf(true))
        );
        
        given(productService.getDealProducts(anyLong())).willReturn(products);

        //when
        mockMvc.perform(get("/deals_of_the_day/2")
                    .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.products.length()").value(products.size()))
            .andExpect(jsonPath("$").isMap());
    }

    @Test
    void testGetDealProductsIsEmpty() throws Exception {
        //given
    
        given(productService.getDealProducts(anyLong())).willReturn(List.of());

        //when
        mockMvc.perform(get("/deals_of_the_day/2"))
            .andExpect(status().isInternalServerError())
            .andExpect(result -> {
                if (result.getResolvedException() instanceof GeneralCustomApplicationException) {
                    assertEquals("not deals products", result.getResolvedException().getMessage());
                } else {
                    throw new AssertionError("Se esperaba una GeneralCustomApplicationException");
                }
            });
    }

    //getProductsByCategory()

    @Test
    void testGetProductsByCategory() throws Exception {
        //given
        List<Product> products = List.of(
            new Product(Long.valueOf(10l),"quest","to play","virtual reality glasses",Float.valueOf(10f),"toys",Boolean.valueOf(true)),
            new Product(Long.valueOf(11l),"some","to play","virtual reality glasses",Float.valueOf(10f),"toys",Boolean.valueOf(true))
        );

        given(productService.getProductsByCategory(anyString())).willReturn(products);

        //when
        mockMvc.perform(get("/products/category")
                        .param("category", "toys")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products.length()").value(products.size()))
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void testGetProductsByCategoryIsEmpty() throws Exception {
        //given

        given(productService.getProductsByCategory(anyString())).willReturn(List.of());

        //when
        mockMvc.perform(get("/products/category")
                        .param("category", "toys")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> {
                    if (result.getResolvedException() instanceof GeneralCustomApplicationException) {
                        assertEquals("not products in this category", result.getResolvedException().getMessage());
                    } else {
                        throw new AssertionError("Se esperaba una GeneralCustomApplicationException");
                    }
                });
    }


    //getAllProducts()

    @Test
    void testGetAllProducts() throws Exception {
         //given
         List<Product> products = List.of(
            new Product(Long.valueOf(10l),"quest","to play","virtual reality glasses",Float.valueOf(10f),"toys",Boolean.valueOf(true)),
            new Product(Long.valueOf(11l),"some","to play","virtual reality glasses",Float.valueOf(10f),"toys",Boolean.valueOf(true))
        );

        //when
        given(productService.getAllProducts()).willReturn(products);

        mockMvc.perform(get("/products")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products.length()").value(products.size()))
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void testGetAllProductsIsEmpty() throws Exception {
        //given

        given(productService.getAllProducts()).willReturn(List.of());

        //when
        mockMvc.perform(get("/products")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> {
                    if (result.getResolvedException() instanceof GeneralCustomApplicationException) {
                        assertEquals("not products avaliables", result.getResolvedException().getMessage());
                    } else {
                        throw new AssertionError("Se esperaba una GeneralCustomApplicationException");
                    }
                });
    }

}
