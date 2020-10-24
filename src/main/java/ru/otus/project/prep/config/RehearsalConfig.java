package ru.otus.project.prep.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rehearsal")
@Data
public class RehearsalConfig {
    private int canBeCancelledBefore;
}
