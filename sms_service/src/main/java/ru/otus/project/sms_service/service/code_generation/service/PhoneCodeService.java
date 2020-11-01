package ru.otus.project.sms_service.service.code_generation.service;

import ru.otus.project.sms_service.domain.SmsCode;
import ru.otus.project.sms_service.dto.SmsCodeStatus;

import java.util.Optional;

public interface PhoneCodeService {
    SmsCode makeCode(String phone);

    SmsCodeStatus checkCode(SmsCode code);
}
