package com.example.schedule1.comment.dto;

import com.example.schedule1.user.entity.User;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String text;
    private Long scheduleId;
    private Long userId;
}
