package com.socket_redis.websocket_redis.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.socket_redis.websocket_redis.Enum.MemberStatus;
import com.socket_redis.websocket_redis.domain.entity.Member;
import com.socket_redis.websocket_redis.domain.entity.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.socket_redis.websocket_redis.domain.entity.QMember.*;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Member findMemberByEmailAndStatus(String email, MemberStatus memberStatus) {
        return queryFactory.selectFrom(member)
                .where(member.email.eq(email), member.memberStatus.eq(MemberStatus.Active))
                .fetchFirst();
    }

    @Override
    public Member findYetMemberByEmailAndStatus(String email, MemberStatus memberStatus, MemberStatus memberStatus2) {
        return queryFactory.selectFrom(member)
                .where(member.email.eq(email),member.memberStatus.in(memberStatus,memberStatus2))
                .fetchFirst();
    }
}
