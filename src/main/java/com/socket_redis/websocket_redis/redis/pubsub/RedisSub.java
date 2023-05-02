package com.socket_redis.websocket_redis.redis.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSub implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessageSendingOperations messageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
            String topic = Objects.requireNonNull(redisTemplate.getStringSerializer().deserialize(message.getChannel()));
            log.info(publishMessage);
            String chatMessage = objectMapper.readValue(publishMessage, String.class);
            messageSendingOperations.convertAndSend(topic, chatMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("chat.redis.RedisSub.onMessage.error");
        }
    }

}