package com.socket_redis.websocket_redis.domain.repository;

import com.socket_redis.websocket_redis.Enum.MemberStatus;
import com.socket_redis.websocket_redis.domain.entity.Member;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface MemberRepositoryCustom {
    Member findMemberByEmailAndStatus(String email, MemberStatus memberStatus);

    Member findYetMemberByEmailAndStatus(String email, MemberStatus memberStatus,MemberStatus memberStatus2);
}
