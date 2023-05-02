package com.socket_redis.websocket_redis;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class WebsocketRedisApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        SpringApplication.run(WebsocketRedisApplication.class, args);
    }


    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory JpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }




}
