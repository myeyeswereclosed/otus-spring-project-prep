package ru.otus.project.prep.service.sms.code_generation.availability;

public interface PhoneCodeGenerationAvailabilityChecker {
    CodeGenerationAvailability run(String phone);
}
