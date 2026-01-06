package com.example.schedule1.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String text;

    public CreateScheduleResponse(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
       }
}
