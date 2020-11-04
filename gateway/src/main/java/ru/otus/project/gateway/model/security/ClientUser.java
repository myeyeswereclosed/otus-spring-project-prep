package ru.otus.project.gateway.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUser {
    private String username;
    private String password;
    private String client_id;
    private String grant_type = "password";
}
