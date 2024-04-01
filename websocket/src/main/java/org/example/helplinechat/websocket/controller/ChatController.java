package org.example.helplinechat.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.example.helplinechat.websocket.entity.ChatMessage;
import org.example.helplinechat.websocket.entity.ChatNotification;
import org.example.helplinechat.websocket.service.ChatMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMsgService;

    // Aqui você cria a fila de mensagens
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage savedMsg = chatMsgService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                         savedMsg.getId(),
                         savedMsg.getSenderId(),
                         savedMsg.getRecipientId(),
                         savedMsg.getContent()
                )
        );
    }


    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
                                                              @PathVariable String recipientId){

        return ResponseEntity.status(200).body(chatMsgService.findChatMessages(senderId, recipientId));
    }
}
