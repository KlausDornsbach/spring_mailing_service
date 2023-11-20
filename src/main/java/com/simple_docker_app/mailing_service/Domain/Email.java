package com.simple_docker_app.mailing_service.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "sender_id")
    List<User> sentToUser;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "receiver_id")
    User sentFromUser;

    LocalDateTime timestamp;

    boolean sent;
}
