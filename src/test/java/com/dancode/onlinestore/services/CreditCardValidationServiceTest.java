package com.dancode.onlinestore.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dancode.onlinestore.exceptions.CreditCardValidationException;

public class CreditCardValidationServiceTest {

    CreditCardValidationService creditCardValidationService;

    @BeforeEach
    void setUp() {
        creditCardValidationService = new CreditCardValidationService();
    }

    @Test
    void testValidate() {
        //given
        String number = "1234567890123456";

        //when

        //then
        assertDoesNotThrow(()-> creditCardValidationService.validate(number));

    }

    @Test
    void testValidateIsInvalidCard() {
        //given
        String number = "1111";

        //when
        //then
        CreditCardValidationException result = assertThrows(CreditCardValidationException.class, ()-> creditCardValidationService.validate(number));
      
        assertEquals(number+" is invalid credit card", result.getMessage());
    }
    
    @Test
    void testValidateIsStolenCard() {
        //given
        String number = "1111111111111111";

        //when
        //then
        CreditCardValidationException result = assertThrows(CreditCardValidationException.class, ()-> creditCardValidationService.validate(number));
      
        assertEquals(number+" is a stolen credit card", result.getMessage());
    }
}
