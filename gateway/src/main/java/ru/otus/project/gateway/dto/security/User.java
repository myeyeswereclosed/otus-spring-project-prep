package ru.otus.project.gateway.dto.security;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {
    private long id;
    private @NonNull String name;
    private @NonNull String phone;
    private String email;
    private @NonNull String password;
    private @NonNull String role;

    public User(String name, String phone, String email, String password, String role) {
        this(0, name, phone, email, password, role);
    }
}
