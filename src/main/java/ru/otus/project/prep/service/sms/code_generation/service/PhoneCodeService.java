package ru.otus.project.prep.service.sms.code_generation.service;

import ru.otus.project.prep.domain.sms.SmsCode;

public interface PhoneCodeService {
    SmsCode makeCode(String phone);
}
