package ru.otus.project.sms_service.service.code_generation.availability;

public interface PhoneCodeGenerationAvailabilityChecker {
    CodeGenerationAvailability run(String phone);
}
