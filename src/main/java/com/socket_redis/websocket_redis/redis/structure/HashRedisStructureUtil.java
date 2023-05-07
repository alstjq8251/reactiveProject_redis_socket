package com.socket_redis.websocket_redis.redis.structure;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HashRedisStructureUtil<T> implements RedisStructureInterfacte<Map<String, T>> {

    private final ReactiveRedisTemplate<String, T> reactiveRedisTemplate;

    @Override
    public Mono<Void> save(String key, Map<String, T> value) {
        return reactiveRedisTemplate.opsForHash().putAll(key, value).then();
    }

    @Override
    public Mono<Map<String, T>> lookup(String key) {
        return reactiveRedisTemplate.<String, T>opsForHash().entries(key)
                .map(entry -> Map.entry(entry.getKey(), entry.getValue()))
                .collectMap(Map.Entry::getKey, Map.Entry::getValue);
    }

    @Override
    public Mono<Void> update(String key, Map<String, T> value) {
        return delete(key).then(save(key, value));
    }

    @Override
    public Mono<Void> delete(String key) {
        return reactiveRedisTemplate.delete(key).then();
    }

    @Override
    public Mono<Boolean> saveExpire(String key, Map<String, T> value, long time, TimeUnit unit) {
        return reactiveRedisTemplate.<String, T>opsForHash().putAll(key, value)
                .then(reactiveRedisTemplate.expire(key, Duration.ofMillis(unit.toMillis(time))));
    }

    @Override
    public Mono<Duration> getExpiration(String key) {
        return reactiveRedisTemplate.getExpire(key);
    }

}