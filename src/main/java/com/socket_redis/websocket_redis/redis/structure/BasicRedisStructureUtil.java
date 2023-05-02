package com.socket_redis.websocket_redis.redis.structure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BasicRedisStructureUtil implements RedisStructureInterfacte<Object>{

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(String key, Object value) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }


    @Override
    public Object lookup(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    @Override
    public void update(String key, Object value) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void saveExpire(String key, Object value, long time, TimeUnit unit) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value,time,unit);
    }

    @Override
    public long getExpiration(String key) {
        return redisTemplate.getExpire(key);
    }
}