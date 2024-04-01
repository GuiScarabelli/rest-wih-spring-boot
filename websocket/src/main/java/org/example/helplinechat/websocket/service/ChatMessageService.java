package org.example.helplinechat.websocket.service;

import lombok.RequiredArgsConstructor;
import org.example.helplinechat.websocket.entity.ChatMessage;
import org.example.helplinechat.websocket.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ChatMessageService {

    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage message){
        var chatId = chatRoomService.getChatRoomId(
                message.getSenderId(),
                message.getRecipientId(),
                true). orElseThrow();

        message.setChatId(chatId);
        repository.save(message);
        return message;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatID = chatRoomService.getChatRoomId(senderId, recipientId, false);

        return chatID.map(repository::findByChatId).orElse(new ArrayList<>());
    }

}
