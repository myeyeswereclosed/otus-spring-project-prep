package ru.otus.project.authorization_server.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDto {
    private long id;
    private @NonNull String name;
    private @NonNull String phone;
    private String email;
    private @NonNull String password;
    private @NonNull String role;
}
