package ru.otus.project.prep.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "timetable")
@Data
public class Timetable {
    private int start;
    private int end;
    private int period;
}
