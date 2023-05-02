package com.socket_redis.websocket_redis.auth.router;


import com.socket_redis.websocket_redis.auth.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuthRouter {

    private final AuthHandler authHandler;
    @Bean
    public RouterFunction<ServerResponse> authRoutes() {
        return RouterFunctions.route()
                .GET("auth/email-validate",authHandler::validateEmail)
                .POST("/auth/sign-up", authHandler::signUpMember)
                .PATCH("/auth/fix-name", authHandler::fixNameMember)
                .DELETE("/auth/withdrawal", authHandler::withDrawalMember)
                .build();
    }

}
