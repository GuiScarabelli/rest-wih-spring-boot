package org.example.helplinechat.websocket.repository;

import org.example.helplinechat.websocket.entity.Users;
import org.example.helplinechat.websocket.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, String> {
    List<Users> findAllByStatus(Status status);
}
