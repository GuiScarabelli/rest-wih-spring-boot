package org.example.helplinechat.websocket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.helplinechat.websocket.enums.Status;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity

public class Users {
    @Id
    @UuidGenerator
    private String id;
    private String nickName;
    private String fullName;
    private Status status; // Aprendi Enum e é mt foda, tmj mac do passado

}
