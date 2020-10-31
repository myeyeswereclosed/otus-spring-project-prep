package ru.otus.project.sms_service.service.code_generation.availability;

import org.springframework.stereotype.Service;

@Service
public class SmsCodeGenerationAvailabilityChecker implements PhoneCodeGenerationAvailabilityChecker {
    public CodeGenerationAvailability run(String phone) {
        /* TODO проверка бизнес-правил - сейчас заглушка */
        return new CodeGenerationAvailability(true, "Ok", phone);
    }
}
