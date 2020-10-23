package ru.otus.project.prep.controller.rest.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.prep.dto.PhoneDto;
import ru.otus.project.prep.service.sms.SmcCodeProvider;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SmcCodeProvider provider;

    @PostMapping("code/generate")
    public ResponseEntity<?> generate(@RequestBody PhoneDto phone) {
        var codeGenerationAvailability = provider.run(phone.getNumber());

        return
            codeGenerationAvailability.isAvailable()
                ? ResponseEntity.ok(codeGenerationAvailability.getMessage())
                : ResponseEntity.badRequest().body(codeGenerationAvailability.getMessage())
            ;
    }
}
