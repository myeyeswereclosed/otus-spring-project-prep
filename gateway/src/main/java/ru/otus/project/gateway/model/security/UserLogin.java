package ru.otus.project.gateway.model.security;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLogin {
    private String phone;
    @ToString.Exclude
    private String password;
}
