package org.example.helplinechat.websocket.service;

import lombok.RequiredArgsConstructor;
import org.example.helplinechat.websocket.entity.Users;
import org.example.helplinechat.websocket.enums.Status;
import org.example.helplinechat.websocket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void saveUser(Users user){
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(Users user){
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }
    public List<Users> findConnectedUser(){
       return repository.findAllByStatus(Status.ONLINE);
    }

}
