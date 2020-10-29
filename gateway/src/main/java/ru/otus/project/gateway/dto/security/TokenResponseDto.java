package ru.otus.project.gateway.dto.security;

import lombok.Data;

@Data
public class TokenResponseDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
}
