package ru.otus.project.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.rehearsal-base.info")
@Data
public class Info {
    private String name;
    private String address;
    private String phone;
}
