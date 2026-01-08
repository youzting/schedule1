package com.example.schedule1.comment.dto;

import lombok.Getter;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long id;
    private final String text;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long userId;

    public GetCommentResponse(Long id, String text, LocalDateTime createdAt, LocalDateTime modifiedAt, Long userId) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

        this.userId = userId;
    }

}
