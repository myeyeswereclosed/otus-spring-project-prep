package ru.otus.project.sms_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SmsService {
    public static void main(String[] args) {
        SpringApplication.run(SmsService.class);
    }
}
