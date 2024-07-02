package com.vasilkov.service.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import java.util.Arrays;
import java.util.Collection;


/**
 * Custom cache resolver for runtime cache names resolution.
 */
class RuntimeCacheResolver extends SimpleCacheResolver {

    /**
     * Constructs a new RuntimeCacheResolver with the specified CacheManager.
     *
     * @param cacheManager The CacheManager to use.
     */
    protected RuntimeCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    /**
     * Retrieves the cache names for the given context.
     *
     * @param context The context of the cache operation invocation.
     * @return The collection of cache names.
     */
    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return Arrays.asList(context.getTarget().getClass().getSimpleName() + "." + context.getMethod().getName());
    }
}