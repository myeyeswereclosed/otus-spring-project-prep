package ru.otus.project.sms_service.service.code_generation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.sms_service.config.SmsCodeConfig;
import ru.otus.project.sms_service.domain.SmsCode;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SmsCodeGenerator implements PhoneCodeGenerator {
    private final SmsCodeConfig config;

    @Override
    public SmsCode generate(String phone) {
        return
            new SmsCode(
                phone,
                String.valueOf(new Random().nextInt(8999) + 1000),
                LocalDateTime.now().plusSeconds(config.getExpirationTimeInSeconds())
            );
    }
}
