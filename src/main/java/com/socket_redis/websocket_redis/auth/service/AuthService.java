package com.socket_redis.websocket_redis.auth.service;

import com.socket_redis.websocket_redis.Enum.MemberStatus;
import com.socket_redis.websocket_redis.domain.entity.Member;
import com.socket_redis.websocket_redis.domain.repository.MemberRepository;
import com.socket_redis.websocket_redis.dto.NameRequestDto;
import com.socket_redis.websocket_redis.dto.SignUpRequestDto;
import com.socket_redis.websocket_redis.dto.WithDrawalRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    public boolean validateEmail(String email) {
        Mono<Member> member = Mono.fromCallable(() ->memberRepository.findMemberByEmailAndStatus(email, MemberStatus.Active));
        return member.block() != null;
    }
    public Mono<Member> signUpMember(SignUpRequestDto sign) {
        return Mono.fromCallable(() ->
                memberRepository.save(Member.builder()
                .email(sign.getEmail())
                .pw(sign.getPassword())
                .build()));
    }

    public Mono<Member> fixNameMember(NameRequestDto nameRequestDto) {
        return Mono.fromCallable(() ->memberRepository.findMemberByEmailAndPw(nameRequestDto.getEmail(), nameRequestDto.getPw()))
                .switchIfEmpty(Mono.error(new RuntimeException("Member not found")))
                .flatMap(member -> {
                    member.modifyMemberProfile(nameRequestDto);
                    return Mono.fromCallable(() -> memberRepository.save(member));
                });
    }

    public Mono<Member> withDrawalMember(WithDrawalRequestDto withDrawalRequestDto) {
        return Mono.fromCallable(() ->memberRepository.findYetMemberByEmailAndStatus(withDrawalRequestDto.getEmail(),MemberStatus.Drop,MemberStatus.Ban))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("맴버가 존재하지 않습니다.")))
                .flatMap(member -> {
                    member.modifyMemberStatus(MemberStatus.Drop);
                    return Mono.fromCallable(() -> memberRepository.save(member));
                });
    }
}
