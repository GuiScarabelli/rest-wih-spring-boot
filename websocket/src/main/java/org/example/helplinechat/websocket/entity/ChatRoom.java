package org.example.helplinechat.websocket.entity;

import jakarta.persistence.Entity;
import lombok.*;
import java.util.UUID;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class ChatRoom {
    @Id
    @UuidGenerator
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;

}
