package ru.otus.project.prep.service.sms.code_generation.service;

import org.springframework.stereotype.Component;
import ru.otus.project.prep.domain.sms.SmsCode;

import java.util.Random;

@Component
public class SmsCodeGenerator implements PhoneCodeGenerator {
    @Override
    public SmsCode generate(String phone) {
        return new SmsCode(phone, String.valueOf(new Random().nextInt(8999) + 1000));
    }
}
