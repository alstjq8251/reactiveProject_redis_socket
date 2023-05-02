package com.socket_redis.websocket_redis.domain.repository;

import com.socket_redis.websocket_redis.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>,MemberRepositoryCustom {

    Member findMemberByEmailAndPw(String email, String pw);
}
