package com.socket_redis.websocket_redis.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String email;
    private String password;
}
