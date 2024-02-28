package com.dancode.onlinestore.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import com.dancode.onlinestore.entities.dtos.PurchaseDto;
import com.dancode.onlinestore.exceptions.CreditCardValidationException;
import com.dancode.onlinestore.exceptions.GeneralCustomApplicationException;
import com.dancode.onlinestore.exceptions.GlobalExceptionHandler;
import com.dancode.onlinestore.services.CreditCardValidationService;
import com.dancode.onlinestore.services.OrderService;

@ExtendWith(MockitoExtension.class)
public class CheckoutpageControllerTest {

    @Mock
    OrderService orderService;

    @Mock
    CreditCardValidationService creditCardValidationService;

    @InjectMocks
    CheckoutpageController checkoutpageController;

    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    String requestBody;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(checkoutpageController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    // createOrder()
    @Test
    void testCreateOrder() throws Exception {
        requestBody = "{\"firstName\":\"dani\",\"lastName\":\"dd\",\"email\":\"cursos@gmail.com\",\"shippingAddress\":\"123 some\",\"creditCard\":\"1111111111111112\",\"products\":[{\"productId\":1,\"quantity\":10},{\"productId\":2,\"quantity\":2}]}";

        mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("success"));
    }

    @Test
    void testCreateOrderMissingFirstName() throws Exception {
        requestBody = "{\"firstName\":\"\",\"lastName\":\"dd\",\"email\":\"cursos@gmail.com\",\"shippingAddress\":\"123 some\",\"creditCard\":\"1111111111111112\",\"products\":[{\"productId\":1,\"quantity\":10},{\"productId\":2,\"quantity\":2}]}";

        mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateOrderMissingLast() throws Exception {
        requestBody = "{\"firstName\":\"dani\",\"lastName\":\"\",\"email\":\"cursos@gmail.com\",\"shippingAddress\":\"123 some\",\"creditCard\":\"1111111111111112\",\"products\":[{\"productId\":1,\"quantity\":10},{\"productId\":2,\"quantity\":2}]}";

        mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateOrderMissingCreditCard() throws Exception {
        requestBody = "{\"firstName\":\"dani\",\"lastName\":\"dd\",\"email\":\"cursos@gmail.com\",\"shippingAddress\":\"123 some\",\"creditCard\":\"\",\"products\":[{\"productId\":1,\"quantity\":10},{\"productId\":2,\"quantity\":2}]}";

        mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
    }

    @Test
    void testCreateOrderInvalidCard() throws Exception {
        requestBody = "{\"firstName\":\"dani\",\"lastName\":\"dd\",\"email\":\"cursos@gmail.com\",\"shippingAddress\":\"123 some\",\"creditCard\":\"1\",\"products\":[{\"productId\":1,\"quantity\":10},{\"productId\":2,\"quantity\":2}]}";

        doThrow(new CreditCardValidationException("is invalid credit card")).when(creditCardValidationService)
                .validate(anyString());

        mockMvc.perform(post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("['is invalid credit card']"));

        verify(creditCardValidationService).validate(argumentCaptor.capture());
        assertEquals("1", argumentCaptor.getValue());
    }

    @Test
    void testCreateOrderProssesingOrders() throws Exception {

        PurchaseDto purchaseDto = new PurchaseDto("dan", "gut", "", "", "1111111111111112", null);
        ;
        BindingResult rb = new BeanPropertyBindingResult(purchaseDto, "purchaseDto");

        doThrow(new GeneralCustomApplicationException("error")).when(orderService).processOrders(purchaseDto);

        try {
            checkoutpageController.createOrder(purchaseDto, rb);

        } catch (Exception e) {
            assertThat(e, instanceOf(GeneralCustomApplicationException.class));
        }

        verify(orderService).processOrders(purchaseDto);

    }

}
