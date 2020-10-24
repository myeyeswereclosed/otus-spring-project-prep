package ru.otus.project.prep.service.sms.mobile_provider;

import ru.otus.project.prep.domain.sms.SmsCode;

public interface MobileProvider {
    void send(SmsCode code);
}
