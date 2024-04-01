package org.example.helplinechat.websocket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {

    @Id
    @UuidGenerator
    private String Id;
    private String chatId;
    private String recipientId;
    private String senderId;
    private String content;
    private Date timestamp;

}
