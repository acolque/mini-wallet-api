package com.acolque.miniwalletapi.services;

import com.acolque.miniwalletapi.datasource.apis.DollarDataSource;
import com.acolque.miniwalletapi.entities.DollarInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
        DollarInfoDto dollarInfoDto = DollarInfoDto.builder().blue(expected).build();
        when(dollarDataSource.getDollarInfo()).thenReturn(Optional.of(dollarInfoDto));

        double result = service.getDollarBluePrice();

        assertEquals(expected, result);
    }

    @Test
    public void testGetDiffBetweenDollarBlueAndOfficialSuccess() {
        String expected = "50%";
        DollarInfoDto dollarInfoDto = DollarInfoDto.builder().blue(500D).oficial(250D).build();
        when(dollarDataSource.getDollarInfo()).thenReturn(Optional.of(dollarInfoDto));

        String result = service.getDiffBetweenDollarBlueAndOfficial();

        assertEquals(expected, result);
    }

    @Test
    public void testGetDiffBetweenDollarBlueAndOfficialNullResponse() {
        String expected = "no data";

        String result = service.getDiffBetweenDollarBlueAndOfficial();

        assertEquals(expected, result);
    }
}
