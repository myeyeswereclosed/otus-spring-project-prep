package ru.otus.project.gateway.service.sms;

import ru.otus.project.gateway.dto.phone.SmsCodeDto;

public interface SmsService {
    boolean generateCode(String phone);

    boolean checkCode(SmsCodeDto smsCode);
}
