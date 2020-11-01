package ru.otus.project.gateway.service.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.project.gateway.dto.phone.PhoneDto;
import ru.otus.project.gateway.dto.room.RoomDto;

import java.util.List;

@FeignClient(name = "sms-service", path = "/sms-service")
public interface SmsServiceClient {
    @PostMapping("/code/generate")
    ResponseEntity<?> generate(@RequestBody PhoneDto phone);
}
