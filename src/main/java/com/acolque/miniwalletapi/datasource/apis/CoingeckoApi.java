package com.acolque.miniwalletapi.datasource.apis;

import com.acolque.miniwalletapi.datasource.CryptoDataSource;
import com.acolque.miniwalletapi.entities.Cryptos;
import com.acolque.miniwalletapi.entities.CryptoInfoDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CoingeckoApi implements CryptoDataSource {
    private static final String PRECISION = "full";
    private static final String CURRENCY = "usd";
    private static final List<Cryptos> COINS = List.of(Cryptos.BITCOIN, Cryptos.ETHEREUM);
    private RestTemplate restTemplate;

    public CoingeckoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CryptoInfoDto> getCryptoInfo() {
        try {
            Map<String, Map<String, Double>> res = restTemplate.exchange(
                    "https://api.coingecko.com/api/v3/simple/price?ids={ids}&vs_currencies={currencies}&precision={precision}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Map<String, Double>>>() {},
                    getCoinsUriParam(), CURRENCY, PRECISION).getBody();

            List<CryptoInfoDto> resultList = res.entrySet().stream()
                    .map(e -> Map.entry(
                            e.getKey(),
                            e.getValue().values().stream().findAny().orElse(0D)))
                    .map(e -> CryptoInfoDto.builder()
                            .coin(e.getKey())
                            .price(e.getValue())
                            .build())
                    .collect(Collectors.toList());

            return resultList;
        } catch (RestClientException ex) {
            throw new RestClientException("Error en la Api de CoinGecko: " + ex.getMessage(), ex);
        }
    }

    private String getCoinsUriParam() {
        return COINS.stream()
                .map(Cryptos::name)
                .collect(Collectors.joining(","));
    }
}
