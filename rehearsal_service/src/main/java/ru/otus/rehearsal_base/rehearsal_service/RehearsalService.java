package ru.otus.rehearsal_base.rehearsal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RehearsalService {
    public static void main(String[] args) {
        SpringApplication.run(RehearsalService.class);
    }
}
