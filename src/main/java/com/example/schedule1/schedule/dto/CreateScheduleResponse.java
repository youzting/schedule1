package com.example.schedule1.schedule.dto;

import com.example.schedule1.user.dto.GetUserResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateScheduleResponse {
    private final Long id;
    private final String title;
    private final String text;
    private final Long userId;


    public CreateScheduleResponse(Long id, String title, String text,  Long userId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.userId = userId;
       }
}
