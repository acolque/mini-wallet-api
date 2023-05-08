package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.services.DollarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WalletControllerTest {

    @Autowired
    private WalletController controller;

    @MockBean
    private DollarService dollarService;

    @Test
    public void testGetDollarBluePriceSuccess() {
        double priceExpected = 500D;
        when(dollarService.getDollarBluePrice()).thenReturn(priceExpected);

        double result = controller.getDollarBluePrice();

        assertEquals(priceExpected, result);
    }
}
