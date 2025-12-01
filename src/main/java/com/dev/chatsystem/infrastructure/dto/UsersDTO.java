package com.dev.chatsystem.infrastructure.dto;

public record UsersDTO(
        Long id,
        String name,
        String email,
        Boolean online
) {}
