package ru.otus.project.gateway.dto.security;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginDto {
    private String phone;
    @ToString.Exclude
    private String password;
}
