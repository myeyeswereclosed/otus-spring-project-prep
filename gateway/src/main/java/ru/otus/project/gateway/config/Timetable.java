package ru.otus.project.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "timetable")
@Data
public class Timetable {
    private int startHour;
    private int endHour;
    private int periodInDays;
}
