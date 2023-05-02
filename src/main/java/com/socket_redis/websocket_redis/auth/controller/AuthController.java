//package com.socket_redis.websocket_redis.auth.controller;
//
//import com.socket_redis.websocket_redis.auth.service.AuthService;
//import com.socket_redis.websocket_redis.dto.NameRequestDto;
//import com.socket_redis.websocket_redis.dto.SignUpRequestDto;
//import com.socket_redis.websocket_redis.dto.WithDrawalRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(value = "/auth")
//public class AuthController {
//    private final AuthService authService;
//
//    @GetMapping(value = "/email-validate")
//    public boolean validateEmail(@RequestParam(name = "email")String email){
//        return authService.validateEmail(email);
//    }
//
//    @PostMapping(value = "/sign-up")
//    public ResponseEntity<?> signUpMember(@RequestBody SignUpRequestDto sign){
//        return authService.signUpMember(sign);
//    }
//
//    @PatchMapping(value = "/fix-name")
//    public ResponseEntity<?> fixNameMember(@RequestBody NameRequestDto nameRequestDto){
//        return authService.fixNameMember(nameRequestDto);
//    }
//
//    @DeleteMapping(value = "/withdrawal")
//    public ResponseEntity<?> withDrawalMember(@RequestBody WithDrawalRequestDto withDrawalRequestDto){
//        return authService.withDrawalMember(withDrawalRequestDto);
//    }
//
//
//}
