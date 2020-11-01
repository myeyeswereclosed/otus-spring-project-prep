package ru.otus.project.gateway.service.sms;

import org.springframework.http.ResponseEntity;

public interface SmsService {
    boolean generateCode(String phone);
}
