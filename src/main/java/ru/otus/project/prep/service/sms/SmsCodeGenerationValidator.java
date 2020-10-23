package ru.otus.project.prep.service.sms;

import org.springframework.stereotype.Service;

@Service
public class SmsCodeGenerationValidator implements PhoneCodeGenerationValidator {
    public GenerationAvailability run(String phone) {
        return new GenerationAvailability(true, "Ok", phone);
    }
}
