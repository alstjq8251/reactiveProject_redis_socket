package com.socket_redis.websocket_redis.redis.structure;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HashRedisStructureUtil<T> implements RedisStructureInterfacte<Map<String, T>> {

    private final RedisTemplate<String, T> redisTemplate;


    @Override
    public void save(String key, Map<String, T> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    @Override
    public Map<String, T> lookup(String key) {
        Map<Object, Object> hash = redisTemplate.opsForHash().entries(key);
        return hash.entrySet().stream()
                .collect(Collectors.toMap(e -> (String) e.getKey(), e -> (T) e.getValue()));
    }

    @Override
    public void update(String key, Map<String, T> value) {
        delete(key);
        save(key, value);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void saveExpire(String key, Map<String, T> value, long time, TimeUnit unit) {
        redisTemplate.opsForHash().putAll(key, value);
        redisTemplate.expire(key, time, unit);
    }

    @Override
    public long getExpiration(String key) {
        return redisTemplate.getExpire(key);
    }
}
