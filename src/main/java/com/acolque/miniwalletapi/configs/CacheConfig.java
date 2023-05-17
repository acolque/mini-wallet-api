package com.acolque.miniwalletapi.configs;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String CRYPTO_YA_API_CACHE_NAME = "cryptoYaApi";

    @Bean
    public CacheManager getCacheManager() {
        return new ConcurrentMapCacheManager(CRYPTO_YA_API_CACHE_NAME);
    }
}
