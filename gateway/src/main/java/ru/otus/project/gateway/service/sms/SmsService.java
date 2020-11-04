package ru.otus.project.gateway.service.sms;

import ru.otus.project.gateway.model.phone.SmsCode;

public interface SmsService {
    boolean generateCode(String phone);

    boolean checkCode(SmsCode smsCode);
}
