package ru.otus.project.sms_service.service.code_generation.availability;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class CodeGenerationAvailability {
    private final boolean isAvailable;
    private final String message;
    private final String phone;
}
