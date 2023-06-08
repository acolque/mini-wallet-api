package com.acolque.miniwalletapi.datasource.apis;

import com.acolque.miniwalletapi.datasource.DollarDataSource;
import com.acolque.miniwalletapi.entities.DollarInfoDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.acolque.miniwalletapi.configs.CacheConfig.CRYPTO_YA_API_CACHE_NAME;

@Component
public class CryptoYaApi implements DollarDataSource {

    private final RestTemplate restTemplate;

    public CryptoYaApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(CRYPTO_YA_API_CACHE_NAME)
    public Optional<DollarInfoDto> getDollarInfo() {
        try {
            DollarInfoDto resultDto = restTemplate.getForObject("https://criptoya.com/api/dolar", DollarInfoDto.class);
            return Optional.ofNullable(resultDto);
        } catch (RestClientException ex) {
            throw new RestClientException(String.format("Error en la api de CryptoYa: %s", ex.getMessage()), ex);
        }
    }

    @CacheEvict(value = CRYPTO_YA_API_CACHE_NAME, allEntries = true)
    @Scheduled(fixedRateString = "${caching.ttl.general}")
    public void clean() {
        System.out.println("limpiando crypto ya api cache");
    }
}
