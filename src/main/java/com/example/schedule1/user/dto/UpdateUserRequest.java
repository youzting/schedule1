package com.example.schedule1.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @Email(message = "이메일 형식이 아닙니다.")
    public String email;
}
