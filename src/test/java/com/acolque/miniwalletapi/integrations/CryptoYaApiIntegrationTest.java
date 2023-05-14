package com.acolque.miniwalletapi.integrations;

import com.acolque.miniwalletapi.datasource.apis.CryptoYaApi;
import com.acolque.miniwalletapi.entities.DollarInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.acolque.miniwalletapi.configs.CacheConfig.CRYPTO_YA_API_CACHE_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CryptoYaApiIntegrationTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CryptoYaApi api;

    @MockBean
    private RestTemplate restTemplate;

    private final String CACHE_NAME = CRYPTO_YA_API_CACHE_NAME;

    @BeforeEach
    public void before() {
        cacheManager.getCache(CACHE_NAME).clear();
    }

    @Test
    public void testCacheAddValueSuccess() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(DollarInfoDto.builder().build());

        Optional<DollarInfoDto> optionalResult = api.getDollarInfo();

        verify(restTemplate, atLeastOnce()).getForObject(anyString(), any());
        assertTrue(optionalResult.isPresent());
        assertCacheValue(optionalResult.get());
    }

    @Test
    public void testCacheValueExistsSuccess() {
        cacheManager.getCache(CACHE_NAME).put(SimpleKey.EMPTY, DollarInfoDto.builder().build());

        Optional<DollarInfoDto> optionalResult = api.getDollarInfo();

        verifyNoInteractions(restTemplate);
        assertTrue(optionalResult.isPresent());
        assertCacheValue(optionalResult.get());
    }

    private void assertCacheValue(DollarInfoDto dto) {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        Cache.ValueWrapper value = cache.get(SimpleKey.EMPTY);
        assertNotNull(value);
        assertEquals(dto, value.get());
    }
}
