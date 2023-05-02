package com.socket_redis.websocket_redis.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class LoginRequestDto {

    @NotBlank(message = "이메일 형식으로 된 ID를 입력해주세요")
    public String Id;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String passWord;

    @Builder
    public LoginRequestDto(String Id, String passWord){
        this.Id=Id;
        this.passWord=passWord;
    }
}
