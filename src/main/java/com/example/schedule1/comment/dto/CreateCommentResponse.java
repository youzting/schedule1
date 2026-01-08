package com.example.schedule1.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String text;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long scheduleId;
    private final Long userId;

    public CreateCommentResponse(Long id, String text, LocalDateTime createdAt, LocalDateTime modifiedAt,  Long scheduleId,  Long userId) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.scheduleId = scheduleId;
        this.userId = userId;
    }


}
