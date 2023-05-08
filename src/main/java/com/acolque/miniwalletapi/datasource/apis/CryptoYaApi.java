package com.acolque.miniwalletapi.datasource.apis;

import com.acolque.miniwalletapi.entities.DollarInfoDto;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CryptoYaApi implements DollarDataSource {

    private final RestTemplate restTemplate;

    public CryptoYaApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<DollarInfoDto> getDollarInfo() {
        try {
            DollarInfoDto resultDto = restTemplate.getForObject("https://criptoya.com/api/dolar", DollarInfoDto.class);
            return Optional.ofNullable(resultDto);
        } catch (RestClientException ex) {
            throw new RestClientException(String.format("Error en la api de CryptoYa: %s", ex.getMessage()), ex);
        }
    }
}
