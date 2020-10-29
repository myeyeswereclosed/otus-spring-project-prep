package ru.otus.project.gateway.dto.security;

import lombok.Data;

@Data
public class UserLoginDto {
    private String phone;
    private String password;
}
