package com.acolque.miniwalletapi.services;

import com.acolque.miniwalletapi.datasource.apis.DollarDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DollarServiceTest {

    @Mock
    private DollarDataSource dollarDataSource;

    @InjectMocks
    private DollarService service;

    @Test
    public void testGetDollarBluePriceSuccess() {
        double expected = 500D;
        when(dollarDataSource.getDollarBluePrice()).thenReturn(expected);

        double result = service.getDollarBluePrice();

        assertEquals(expected, result);
    }

    @Test
    public void testGetDiffBetweenDollarBlueAndOfficial() {
        String expected = "50%";
        when(dollarDataSource.getDollarBluePrice()).thenReturn(500D);
        when(dollarDataSource.getDollarOfficial()).thenReturn(250D);

        String result = service.getDiffBetweenDollarBlueAndOfficial();

        assertEquals(expected, result);
    }
}
