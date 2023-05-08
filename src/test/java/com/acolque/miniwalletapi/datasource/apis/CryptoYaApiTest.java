package com.acolque.miniwalletapi.datasource.apis;

import com.acolque.miniwalletapi.entities.DollarInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testGetDollarInfoSuccess() {
        double expected = 500D;
        when(restTemplate.getForObject(anyString(), any())).thenReturn(DollarInfoDto.builder().blue(expected).build());

        Optional<DollarInfoDto> result = api.getDollarInfo();

        assertTrue(result.isPresent());
        assertEquals(expected, result.get().getBlue());
    }

    @Test
    public void testGetDollarInfoNullResponse() {
        Optional<DollarInfoDto> result = api.getDollarInfo();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetDollarInfoThrows() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RestClientException.class);

        assertThrows(RestClientException.class, () -> api.getDollarInfo());
    }
}
