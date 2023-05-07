package com.socket_redis.websocket_redis.redis.pubsub;

import com.socket_redis.websocket_redis.redis.structure.HashRedisStructureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.DefaultMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
@Component
public class RedisRepositoryInit {

    private final ReactiveRedisSub redisSub;
    private final ReactiveRedisMessageListenerContainer redisMessageListenerContainer;
    private final HashRedisStructureUtil<Object> redisHashUtil;
    private final Map<String, Disposable> subscriptions = new ConcurrentHashMap<>();
    @PostConstruct
    private void init() {
        redisHashUtil.lookup("topic")
                .flatMapMany(entry -> Flux.fromIterable(entry.entrySet()))
                .flatMap(entry -> {
                    ChannelTopic topic = ChannelTopic.of(entry.getKey());
                    Disposable disposable = redisMessageListenerContainer.receive(topic)
                            .flatMap(message -> {
                                Message redisMessage = new DefaultMessage(message.getChannel().getBytes(), message.getMessage().getBytes());
                                return redisSub.handleMessage(redisMessage, null);
                            })
                            .onErrorContinue((error, obj) -> {
                                // 여기에 에러 처리 로직을 추가하십시오.
                            })
                            .subscribe();

                    subscriptions.put(entry.getKey(), disposable);
                    return Mono.empty();
                })
                .subscribe();
    }

    public Mono<Void> subscribe(String topicKey) {
        return redisHashUtil.lookup("topic")
                .flatMap(topicMap -> {
                    if (topicMap.get(topicKey) == null) {
                        ChannelTopic topic = ChannelTopic.of(topicKey);

                        // 리액티브 메시지 리스너 추가 및 구독
                        Disposable disposable = redisMessageListenerContainer.receive(topic)
                                .flatMap(message -> {
                                    Message redisMessage = new DefaultMessage(message.getChannel().getBytes(), message.getMessage().getBytes());
                                    return redisSub.handleMessage(redisMessage, null);
                                })
                                .onErrorContinue((error, obj) -> {
                                    // 여기에 에러 처리 로직을 추가하십시오.
                                })
                                .subscribe();

                        subscriptions.put(String.valueOf(topic), disposable);
                        return Mono.empty();
                    } else {
                        return Mono.empty();
                    }
                });
    }

    @PreDestroy
    private void rollback() {
        subscriptions.forEach((key, disposable) -> {
            disposable.dispose();
        });

}