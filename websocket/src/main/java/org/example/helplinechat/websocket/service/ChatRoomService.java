package org.example.helplinechat.websocket.service;

import lombok.RequiredArgsConstructor;
import org.example.helplinechat.websocket.entity.ChatRoom;
import org.example.helplinechat.websocket.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ChatRoomService {
    private final ChatRoomRepository repository;
        public Optional<String> getChatRoomId(String senderId, String recipientId, Boolean roomExists){
            return repository
                    .findBySenderIdAndRecipientId(senderId, recipientId)
                    .map(ChatRoom::getChatId)
                    .or(() -> {
                        if(roomExists) {
                            var chatId = createChatId(senderId, recipientId);
                            return Optional.of(chatId);
                        }
                        return Optional.empty();
                    });
        }

    private String createChatId(String senderID, String recipientId){
        // O idd ddo chat vai ser quem mandou e para quem.
       var chatId = String.format("%s_%s", senderID, recipientId);

       // Cria o chat para quem ta enviando
       ChatRoom senderRecipient = ChatRoom.builder()
               .chatId(chatId)
               .senderId(senderID)
               .recipientId(recipientId)
               .build();

       // Cria o chat de quem vai receber
       ChatRoom recipientSender = ChatRoom.builder()
               .chatId(chatId)
               .senderId(recipientId)
               .recipientId(senderID)
               .build();

       repository.save(senderRecipient);
       repository.save(recipientSender);
        return chatId;
    }

}
