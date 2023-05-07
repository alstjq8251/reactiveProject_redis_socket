package com.socket_redis.websocket_redis.redis.structure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BasicRedisStructureUtil implements RedisStructureInterfacte<Object>{

    private final ReactiveRedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<Void> save(String key, Object value) {
        ReactiveValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.set(key, value).then();
    }

    @Override
    public Mono<Object> lookup(String key) {
        ReactiveValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }


    @Override
    public Mono<Void> update(String key, Object value) {
        ReactiveValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.set(key, value).then();
    }

    @Override
    public Mono<Void> delete(String key) {
        return redisTemplate.delete(key).then();
    }

    @Override
    public Mono<Boolean> saveExpire(String key, Object value, long time, TimeUnit unit) {
        ReactiveValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.set(key, value, Duration.ofMillis(TimeUnit.MILLISECONDS.convert(time, unit)));
    }

    @Override
    public Mono<Duration> getExpiration(String key) {
        return redisTemplate.getExpire(key);
    }
}