package com.socket_redis.websocket_redis.dto;

import lombok.Data;

@Data
public class NameRequestDto {

    private String email;

    private String name;

    private String pw;
}
