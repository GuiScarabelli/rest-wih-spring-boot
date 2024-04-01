package org.example.helplinechat.websocket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatNotification {
    @Id
    @UuidGenerator
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
