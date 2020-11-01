package ru.otus.project.gateway.controller.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.gateway.dto.phone.PhoneDto;
import ru.otus.project.gateway.dto.phone.SmsCodeDto;
import ru.otus.project.gateway.service.sms.SmsService;
import ru.otus.project.gateway.service.user.UserAuthenticationService;
import ru.otus.project.gateway.service.user.UserService;

@RestController
@RequiredArgsConstructor
public class SmsCodeController {
    private final SmsService smsService;

    @PostMapping("/smsCode/generate")
    public ResponseEntity<?> generateCode(@RequestBody PhoneDto phone) {
        System.out.println("GNERETING CODE " + phone);

        // TODO check phone
        return smsService.generateCode(phone.getNumber()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
