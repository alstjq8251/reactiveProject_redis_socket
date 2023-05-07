package com.socket_redis.websocket_redis.redis.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactiveRedisSub implements MessageListener {

    private final ObjectMapper objectMapper;
    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;
    private final ReactiveWebSocketSessionManager reactiveWebSocketSessionManager;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        handleMessage(message,pattern);
    }

    public Mono<Void> handleMessage(Message message, byte[] pattern) {
        return Mono.just(message)
                .map(msg -> reactiveRedisTemplate.getSerializationContext().getValueSerializationPair().read(ByteBuffer.wrap(msg.getBody())).toString())
                .doOnNext(log::info)
                .flatMap(chatMessage -> {
                    try {
                        String topic = reactiveRedisTemplate.getSerializationContext().getKeySerializationPair().getReader()
                                .read(ByteBuffer.wrap(message.getChannel()));
                        reactiveWebSocketSessionManager.sendToAll(topic, chatMessage);
                        return Mono.empty();
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                })
                .onErrorResume(e -> {
                    log.error("chat.redis.RedisSub.onMessage.error", e);
                    return Mono.empty();
                })
                .then();
    }
}