package ru.otus.project.prep.service.sms;

public interface PhoneCodeGenerationValidator {
    GenerationAvailability run(String phone);
}
