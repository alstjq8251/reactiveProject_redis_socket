package com.socket_redis.websocket_redis.redis.pubsub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactiveRedisPub {

    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    public Mono<Long> publish(ChannelTopic topic, String message) {
        return reactiveRedisTemplate.convertAndSend(topic.getTopic(), message);
    }

}