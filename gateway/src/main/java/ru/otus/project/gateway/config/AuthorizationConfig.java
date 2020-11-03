package ru.otus.project.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.oauth2")
@Data
public class AuthorizationConfig {
    private String clientId;
    private String clientSecret;
    private String signingKey;
}
