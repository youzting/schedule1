package com.example.schedule1.schedule.dto;

import java.time.LocalDateTime;

public record SchedulePageResponse (
    Long id,
    String title,
    String text,
    String username,
    Long commentCount,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt

){}