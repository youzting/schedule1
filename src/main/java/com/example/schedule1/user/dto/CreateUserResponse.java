package com.example.schedule1.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifieddAt;

    public CreateUserResponse(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifieddAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifieddAt = modifieddAt;
    }
}
