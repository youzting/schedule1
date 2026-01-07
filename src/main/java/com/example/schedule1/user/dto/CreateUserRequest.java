package com.example.schedule1.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotBlank
    private String username;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
}
