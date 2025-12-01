package com.dev.chatsystem.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menssage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "message_id", nullable = false, unique = true, length = 36)
    private String id;

    @Column(name = "message_content", nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
