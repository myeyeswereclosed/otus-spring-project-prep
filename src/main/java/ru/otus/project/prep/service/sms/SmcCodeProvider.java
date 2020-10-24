package ru.otus.project.prep.service.sms;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.project.prep.service.sms.code_generation.availability.CodeGenerationAvailability;

@MessagingGateway
public interface SmcCodeProvider {
    @Gateway(
        requestChannel = "requestChannel",
        replyChannel = "responseChannel"
    )
    CodeGenerationAvailability run(String phone);
}
