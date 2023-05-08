package com.acolque.miniwalletapi.controllers;

import com.acolque.miniwalletapi.services.DollarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @Test
    public void testGetDollarBluePriceThrows() {
        when(dollarService.getDollarBluePrice()).thenThrow(RestClientException.class);

        assertThrows(RestClientException.class, () -> controller.getDollarBluePrice());
    }
}
