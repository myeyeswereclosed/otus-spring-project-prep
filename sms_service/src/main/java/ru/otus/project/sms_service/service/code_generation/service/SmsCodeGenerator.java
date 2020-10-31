package ru.otus.project.sms_service.service.code_generation.service;

import org.springframework.stereotype.Component;
import ru.otus.project.sms_service.domain.SmsCode;

import java.util.Random;

@Component
public class SmsCodeGenerator implements PhoneCodeGenerator {
    @Override
    public SmsCode generate(String phone) {
        return new SmsCode(phone, String.valueOf(new Random().nextInt(8999) + 1000));
    }
}
