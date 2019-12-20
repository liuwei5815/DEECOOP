package com.yvan.spring.autoconfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.yvan.cache.YvanRedisCacheManager;
import com.yvan.platform.JsonWapperSerializer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

@Configuration
@ConditionalOnBean(annotation = {EnableCaching.class})
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAutoConfiguration.class);
    public static final ObjectMapper OM;

    @Value("${spring.redis.key_prefix}")
    public String cachePrefix = "";

    @Value("${spring.cache.expire-time-seconds}")
    public long cacheExpireTimeOfSeconds = 300;

    static {
        OM = new ObjectMapper();
        OM.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        OM.registerModule(new JodaModule());
        OM.registerModule(JsonWapperSerializer.MODEL);
        OM.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //OM.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //OM.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    @Bean
    public CacheManager cacheManager(@Qualifier("secondaryStringRedisTemplate") RedisTemplate redisTemplate) {
        //RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        YvanRedisCacheManager redisCacheManager = new YvanRedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(cacheExpireTimeOfSeconds);

        final String cp = StringUtils.isEmpty(cachePrefix) ? "" :
                cachePrefix.endsWith(":") ? cachePrefix : cachePrefix + ":";

        redisCacheManager.setUsePrefix(true);
        redisCacheManager.setCachePrefix(new RedisCachePrefix() {
            @Override
            public byte[] prefix(String cacheName) {
                if (StringUtils.isNotEmpty(cacheName)) {
                    if (!cacheName.endsWith(":")) {
                        cacheName = cacheName + ":";
                    }
                }
                final String cp2 = StringUtils.isEmpty(cacheName) ? "" :
                        cacheName.endsWith(":") ? cacheName : cacheName + ":";
                return (cp + cp2).getBytes();
            }
        });

        return redisCacheManager;
    }

    @SuppressWarnings("unchecked")
    @Bean(name = "secondaryStringRedisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(OM);
        //template.setValueSerializer(serializer);

        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        serializer.setObjectMapper(OM);
        template.setValueSerializer(serializer);

        template.setKeySerializer(new StringRedisSerializer(Charset.forName("UTF-8")));
        template.afterPropertiesSet();

        return template;
    }
}