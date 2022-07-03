package com.ryland.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class BaseConfig {

    @Bean
    public JedisClientConfiguration jedisClientConfiguration() {
        return JedisClientConfiguration.builder()
                .clientName("Ryland-Redis-Client")
                .build();
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        return new RedisStandaloneConfiguration("110.42.187.113", 6379);
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(RedisStandaloneConfiguration standaloneConfiguration,
                                                         JedisClientConfiguration clientConfiguration) {
        return new JedisConnectionFactory(standaloneConfiguration, clientConfiguration);
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        return new StringRedisTemplate(jedisConnectionFactory);
    }

}
