package ru.otus.project.sms_service.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sms-code")
@Data
@NoArgsConstructor
public class SmsCodeConfig {
    private int expirationTimeInSeconds;
    private int waitingInterval;
    private int countRequestsInterval;
    private int requestsNumber;
}
