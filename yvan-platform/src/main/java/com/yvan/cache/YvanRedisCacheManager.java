package com.yvan.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Administrator on 2017/11/21.
 */
public class YvanRedisCacheManager extends RedisCacheManager {

    /**
     * Construct a {@link RedisCacheManager}.
     *
     * @param redisOperations
     */
    @SuppressWarnings("rawtypes")
    public YvanRedisCacheManager(RedisOperations redisOperations) {
        this(redisOperations, Collections.<String> emptyList());
    }

    /**
     * Construct a static {@link RedisCacheManager}, managing caches for the specified cache names only.
     *
     * @param redisOperations
     * @param cacheNames
     * @since 1.2
     */
    @SuppressWarnings("rawtypes")
    public YvanRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames) {
        super(redisOperations, cacheNames, false);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected RedisCache createCache(String cacheName) {

        long expiration = computeExpiration(cacheName);
        return new YvanRedisCache(cacheName, (isUsePrefix() ? getCachePrefix().prefix(cacheName) : null), getRedisOperations(), expiration,
                false);
    }
}
