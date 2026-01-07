package com.example.schedule1.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank
    @Email(message = "이메일 형식이 아님")
    private String email;

    @NotBlank
    private String password;
}
