package com.dancode.onlinestore.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancode.onlinestore.entities.Product;
import com.dancode.onlinestore.entities.dtos.PurchaseDto;
import com.dancode.onlinestore.entities.dtos.PurchaseDto.ProductInfo;
import com.dancode.onlinestore.exceptions.GeneralCustomApplicationException;
import com.dancode.onlinestore.repositories.OrderRepository;
import com.dancode.onlinestore.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {


    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    OrderService orderService;

    

    @BeforeEach
    public void setUp() {
        // Configuración manual del valor predeterminado
        ReflectionTestUtils.setField(orderService, "maxNumberOfOrders", 25L);
    }

    @Test
    void testProcessOrders() throws Exception {
        //given
        List<ProductInfo> products = List.of(
            new ProductInfo(Long.valueOf(1001),Long.valueOf(3)),
            new ProductInfo(Long.valueOf(1002),Long.valueOf(2)),
            new ProductInfo(Long.valueOf(1003),Long.valueOf(5))
        );
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setProducts(products);
      
        given(productRepository.findById(anyLong())).willReturn(Optional.of(new Product()));


        //when
        orderService.processOrders(purchaseDto);

        //then
        verify(productRepository,atLeast(1)).findById(anyLong());
        verify(orderRepository,atLeast(1)).save(any());

    }

    @Test
    void testProcessOrdersProductsIsNull() throws Exception {
        //given
       
        PurchaseDto purchaseDto = new PurchaseDto();
      
        //when
        //then
        GeneralCustomApplicationException e = assertThrows(GeneralCustomApplicationException.class, ()->orderService.processOrders(purchaseDto), "this product list is empty");

    }

    @Test
    void testProcessOrdersLimitExceded() throws Exception {
        //given
        List<ProductInfo> products = List.of(
            new ProductInfo(Long.valueOf(1001),Long.valueOf(25)),
            new ProductInfo(Long.valueOf(1002),Long.valueOf(10)),
            new ProductInfo(Long.valueOf(1003),Long.valueOf(10))
        );

        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setProducts(products);
      
        //when

        //then
        GeneralCustomApplicationException e = assertThrows(GeneralCustomApplicationException.class, ()->orderService.processOrders(purchaseDto), "You exceeded the limit of items per order");

    }

    @Test
    void testProcessOrdersProductIdNotFound() throws Exception {
        //given
        List<ProductInfo> products = List.of(
            new ProductInfo(Long.valueOf(1001),Long.valueOf(10)),
            new ProductInfo(Long.valueOf(1002),Long.valueOf(10)),
            new ProductInfo(Long.valueOf(1003),Long.valueOf(10))
        );

        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setProducts(products);

        given(productRepository.findById(anyLong())).willReturn(Optional.empty());

        //when

        //then
        GeneralCustomApplicationException e = assertThrows(GeneralCustomApplicationException.class, ()->orderService.processOrders(purchaseDto), "Product not found");

    }


}
