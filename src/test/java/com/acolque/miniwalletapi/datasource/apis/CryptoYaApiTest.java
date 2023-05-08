package com.acolque.miniwalletapi.datasource.apis;

import com.acolque.miniwalletapi.entities.CryptoYaDollarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CryptoYaApiTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CryptoYaApi api;

    @Test
    public void testGetDollarBluePriceSuccess() {
        double expected = 500D;
        when(restTemplate.getForObject(anyString(), any())).thenReturn(CryptoYaDollarDto.builder().blue(expected).build());

        double result = api.getDollarBluePrice();

        assertEquals(expected, result);
    }

    @Test
    public void testGetDollarBluePriceNullResponse() {
        double expected = 0D;

        double result = api.getDollarBluePrice();

        assertEquals(expected, result);
    }

    @Test
    public void testGetDollarBluePriceThrows() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);

        assertThrows(RestClientException.class, () -> api.getDollarBluePrice());
    }
}
