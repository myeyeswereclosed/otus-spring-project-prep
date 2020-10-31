package ru.otus.project.sms_service.service.mobile_provider;

import ru.otus.project.sms_service.domain.SmsCode;

public interface MobileProvider {
    void send(SmsCode code);
}
