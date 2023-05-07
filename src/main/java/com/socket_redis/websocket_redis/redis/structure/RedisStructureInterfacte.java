package com.socket_redis.websocket_redis.redis.structure;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public interface RedisStructureInterfacte<T> {

    Mono<Void> save(String key , T value);

    Mono<T> lookup(String key);

    Mono<Void> update(String key, T value);

    Mono<Void> delete(String key);

    Mono<Boolean> saveExpire(String key , T value , long time, TimeUnit unit);

    Mono<Duration> getExpiration(String key);

}
