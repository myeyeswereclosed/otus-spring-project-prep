package ru.otus.project.prep.service.sms;

import ru.otus.project.prep.domain.sms.SmsCode;

public interface MobileProvider {
    void sendSms(SmsCode code);
}
