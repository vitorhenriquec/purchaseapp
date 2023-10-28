package com.vitor.bezerra.purchaseapp.infrastructure.configuration;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {
    private static final String CACHE_NAME = "FISCAL_DATA_CACHE";

    @Bean
    public CacheManager cacheManager() {
        var cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(
                List.of(
                        createCacheManager()
                )
        );
        return cacheManager;
    }

    private CaffeineCache createCaffeineCache() {
        final var caffeine = Caf
    }
}
