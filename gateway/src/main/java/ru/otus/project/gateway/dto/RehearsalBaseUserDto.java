package ru.otus.project.gateway.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RehearsalBaseUserDto {
    private long id;
    private @NonNull String name;
    private @NonNull String phone;
    private String email;
    private @NonNull String password;
    private @NonNull String role;
}
