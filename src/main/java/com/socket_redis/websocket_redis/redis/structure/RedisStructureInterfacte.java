package com.socket_redis.websocket_redis.redis.structure;

import java.util.concurrent.TimeUnit;

public interface RedisStructureInterfacte<T> {

    void save(String key , T value);

    T lookup(String key);

    void update(String key, T value);

    void delete(String key);

    void saveExpire(String key , T value , long time, TimeUnit unit);

    long getExpiration(String key);

}
