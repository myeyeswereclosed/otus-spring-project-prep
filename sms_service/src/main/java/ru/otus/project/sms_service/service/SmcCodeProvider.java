package ru.otus.project.sms_service.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.project.sms_service.service.code_generation.availability.CodeGenerationAvailability;

@MessagingGateway
public interface SmcCodeProvider {
    @Gateway(
        requestChannel = "requestChannel",
        replyChannel = "responseChannel"
    )
    CodeGenerationAvailability run(String phone);
}
