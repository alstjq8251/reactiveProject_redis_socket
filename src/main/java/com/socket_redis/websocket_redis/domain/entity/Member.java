package com.socket_redis.websocket_redis.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socket_redis.websocket_redis.Enum.Authority;
import com.socket_redis.websocket_redis.Enum.ChatRoomStatus;
import com.socket_redis.websocket_redis.Enum.MemberStatus;
import com.socket_redis.websocket_redis.dto.NameRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "MEMBER", indexes = {
        @Index(name = "EMAIL_STATUS", columnList = "EMAIL,STATUS")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PW")
    private String pw;

    @Column(name = "AUTHORITY")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "SOCIAL_TYPE")
    private String socialType;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ChatRoom.class)
    @JoinColumn(name ="CR_ID")
    private ChatRoom chatRoom;

    @Builder
    private Member(
            String name,
            String email,
            String pw,
            Authority authority,
            String socialType,
            MemberStatus memberStatus,
            ChatRoom chatRoom
    ){
        this.name=name;
        this.email=email;
        this.pw=pw;
        this.authority=authority;
        this.socialType=socialType;
        this.memberStatus=memberStatus;
        this.chatRoom=chatRoom;
        this.chatRoom.getMemberList().add(this);
    }

    public void modifyMemberProfile(NameRequestDto nameRequestDto){
        this.name=nameRequestDto.getName();
        this.pw=nameRequestDto.getPw();
        this.email=nameRequestDto.getEmail();
    }

    public void modifyMemberStatus(MemberStatus memberStatus){
        this.memberStatus=memberStatus;
    }
}
