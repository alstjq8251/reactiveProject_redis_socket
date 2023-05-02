package com.socket_redis.websocket_redis.auth.handler;


import com.socket_redis.websocket_redis.auth.service.AuthService;
import com.socket_redis.websocket_redis.dto.NameRequestDto;
import com.socket_redis.websocket_redis.dto.SignUpRequestDto;
import com.socket_redis.websocket_redis.dto.WithDrawalRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthHandler {

    private final AuthService authService;
    public Mono<ServerResponse> validateEmail(ServerRequest request) {
        log.info("validateEmail called with request: {}",request);
        String email = request.queryParam("email").orElse("");
        boolean result = authService.validateEmail(email);
        return ServerResponse.ok().bodyValue(result);
    }

    public Mono<ServerResponse> signUpMember(ServerRequest request) {
        Mono<SignUpRequestDto> body = request.bodyToMono(SignUpRequestDto.class);
        return body.flatMap(sign -> authService.signUpMember(sign)
                        .map(ResponseEntity::ok)
                        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                        .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build())))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> fixNameMember(ServerRequest request) {
        Mono<NameRequestDto> body = request.bodyToMono(NameRequestDto.class);
        return body.flatMap(name -> authService.fixNameMember(name)
                        .map(ResponseEntity::ok)
                        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                        .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build())))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> withDrawalMember(ServerRequest request) {
        Mono<WithDrawalRequestDto> body = request.bodyToMono(WithDrawalRequestDto.class);
        return body.flatMap(withDrawal -> authService.withDrawalMember(withDrawal)
                        .map(ResponseEntity::ok)
                        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                        .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build())))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

}
