package ru.otus.project.prep.service.sms;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SmcCodeProvider {
    @Gateway(
        requestChannel = "requestChannel",
        replyChannel = "responseChannel"
    )
    CheckerResult run(String phone);
}
