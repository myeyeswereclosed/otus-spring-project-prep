package ru.otus.project.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server.servlet")
@Data
public class PathConfig {
    private String contextPath;
}

