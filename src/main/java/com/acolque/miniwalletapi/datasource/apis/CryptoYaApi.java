package com.acolque.miniwalletapi.datasource.apis;

import com.acolque.miniwalletapi.entities.CryptoYaDollarDto;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CryptoYaApi implements DollarDataSource {

    private final RestTemplate restTemplate;

    public CryptoYaApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double getDollarBluePrice() {
        try {
            CryptoYaDollarDto resultDto = restTemplate.getForObject("https://criptoya.com/api/dolar", CryptoYaDollarDto.class);
            return Objects.nonNull(resultDto) ? resultDto.getBlue() : 0D;
        } catch (RestClientException ex) {
            throw new RestClientException(String.format("Error en la api de CryptoYa: %s", ex.getMessage()), ex);
        }
    }

    @Override
    public double getDollarOfficial() {
        try {
            CryptoYaDollarDto resultDto = restTemplate.getForObject("https://criptoya.com/api/dolar", CryptoYaDollarDto.class);
            return Objects.nonNull(resultDto) ? resultDto.getOficial() : 0D;
        } catch (RestClientException ex) {
            throw new RestClientException(String.format("Error en la api de CryptoYa: %s", ex.getMessage()), ex);
        }
    }
}
