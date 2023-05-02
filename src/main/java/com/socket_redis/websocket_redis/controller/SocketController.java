//package com.socket_redis.websocket_redis.controller;
//
//import com.socket_redis.websocket_redis.domain.entity.Member;
//import com.socket_redis.websocket_redis.domain.repository.MemberRepository;
//import com.socket_redis.websocket_redis.redis.structure.HashRedisStructureUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@RestController
//public class SocketController {
//
//    private final HashRedisStructureUtil<Object> redisStructureUtil;
//
//    private final MemberRepository memberRepository;
//
//    @GetMapping(value = "/test")
//    public void test(){
//        Map<String,Object> testMap = new HashMap<>();
//        testMap.put("ts1","test2");
//        redisStructureUtil.save("hi",testMap);
//    }
//
//    @GetMapping(value = "/test/test1")
//    public ResponseEntity<?> test1(){
//        Member member = Member.builder()
//                .name("민수")
//                .build();
//        Member member1 = memberRepository.save(member);
//        return ResponseEntity.ok().body(member1);
//    }
//}
