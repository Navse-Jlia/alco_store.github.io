package com.vasilkov.service.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for caching.
 *
 * @author Artem Vasilkov
 */
@Configuration
public class CachingConfiguration extends CachingConfigurerSupport {
    /**
     * Name of the cache resolver.
     */
    public static final String CACHE_RESOLVER_NAME = "simpleCacheResolver";

    /**
     * Provides a CacheResolver bean.
     *
     * @param cacheManager The CacheManager to use.
     * @return The CacheResolver bean.
     */
    @Bean(CACHE_RESOLVER_NAME)
    public CacheResolver cacheResolver(CacheManager cacheManager) {
        return new RuntimeCacheResolver(cacheManager);
    }
}

