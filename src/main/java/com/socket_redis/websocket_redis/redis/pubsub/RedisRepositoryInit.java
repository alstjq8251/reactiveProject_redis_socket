package com.socket_redis.websocket_redis.redis.pubsub;

import com.socket_redis.websocket_redis.redis.structure.HashRedisStructureUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;


@RequiredArgsConstructor
@Component
public class RedisRepositoryInit {

    private final RedisSub redisSub;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final HashRedisStructureUtil<Object> redisHashUtil;

    @PostConstruct
    private void init() {
        Map<String,Object> topicMap = redisHashUtil.lookup("topic");
        topicMap.forEach((key, value) -> redisMessageListenerContainer.addMessageListener(redisSub, ChannelTopic.of(key)));
    }

    public void subscribe(String topic_key) {
        Map<String,Object> topicMap = redisHashUtil.lookup("topic");
        if (topicMap.get(topic_key) == null) {
            ChannelTopic topic = ChannelTopic.of(topic_key);
            redisMessageListenerContainer.addMessageListener(redisSub, topic);
            topicMap.put(topic_key, topic);
            redisHashUtil.save("topic",topicMap);
        }
    }

    @PreDestroy
    private void rollback(){
        Map<String,Object> topicMap = redisHashUtil.lookup("topic");
        topicMap.forEach((key, value) -> redisMessageListenerContainer.removeMessageListener(redisSub, ChannelTopic.of(key)));
    }

}