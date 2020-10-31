package ru.otus.project.sms_service.service.code_generation.service;

import ru.otus.project.sms_service.domain.SmsCode;

public interface PhoneCodeService {
    SmsCode makeCode(String phone);
}
