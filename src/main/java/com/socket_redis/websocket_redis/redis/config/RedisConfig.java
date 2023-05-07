package com.socket_redis.websocket_redis.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.ClientResources;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final ObjectMapper objectMapper;

    @Value("${spring.redis.host}")
    private String host;


    @Value("${spring.redis.port}")
    private int port;


    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration() {
        return LettuceClientConfiguration.builder()
                .clientOptions(ClientOptions.builder().build())
                .clientResources(ClientResources.builder().build())
                .build();
    }

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port), lettuceClientConfiguration());
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate() {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(objectMapper);
        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext.<String, Object>newSerializationContext()
                .key(new StringRedisSerializer())
                .value(serializer)
                .hashKey(new StringRedisSerializer())
                .hashValue(serializer)
                .build();

        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory(), serializationContext);
    }

    @Bean
    public ReactiveRedisMessageListenerContainer reactiveRedisMessageListenerContainer() {
        return new ReactiveRedisMessageListenerContainer(reactiveRedisConnectionFactory());
    }


}