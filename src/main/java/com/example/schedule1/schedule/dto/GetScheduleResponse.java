package com.example.schedule1.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {
    private final Long id;
    private final String title;
    private final String text;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long userId;

    public GetScheduleResponse(Long id, String title, String text, LocalDateTime createdAt, LocalDateTime modifiedAt,  Long userId)
    {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.userId = userId;
    }
}
