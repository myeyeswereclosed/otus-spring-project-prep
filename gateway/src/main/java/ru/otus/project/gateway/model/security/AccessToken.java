package ru.otus.project.gateway.model.security;

import lombok.Data;

@Data
public class AccessToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
}
