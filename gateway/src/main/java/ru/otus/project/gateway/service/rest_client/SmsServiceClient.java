package ru.otus.project.gateway.service.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.project.gateway.model.phone.Phone;
import ru.otus.project.gateway.model.phone.SmsCode;

@FeignClient(name = "sms-service", path = "/sms-service")
public interface SmsServiceClient {
    @PostMapping("/code/generate")
    ResponseEntity<?> generate(@RequestBody Phone phone);

    @PostMapping("/code/check")
    ResponseEntity<?> check(@RequestBody SmsCode smsCode);
}
