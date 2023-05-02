package com.socket_redis.websocket_redis.domain.entity;

import com.socket_redis.websocket_redis.Enum.ChatRoomStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "CHAT_ROOM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROOMNAME")
    private String room_name;

    @Column(name = "ROOM_INITIATOR")
    private String room_initiator;

    @Column(name = "LAST_MESSAGE_TIME")
    private LocalDateTime lastMessageTime;

    @Column
    @Enumerated(EnumType.STRING)
    private ChatRoomStatus chatRoomStatus;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "chatRoom",cascade = CascadeType.REMOVE)
    private List<Member> memberList = new ArrayList<>();

    @Builder
    public ChatRoom(
            String room_name,
            String room_initiator,
            LocalDateTime lastMessageTime,
            ChatRoomStatus chatRoomStatus
    ){
        this.room_name=room_name;
        this.room_initiator=room_initiator;
        this.lastMessageTime=lastMessageTime;
        this.chatRoomStatus=chatRoomStatus;
    }

}
