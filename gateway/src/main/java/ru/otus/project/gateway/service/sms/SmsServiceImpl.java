package ru.otus.project.gateway.service.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.model.phone.Phone;
import ru.otus.project.gateway.model.phone.SmsCode;
import ru.otus.project.gateway.service.rest_client.SmsServiceClient;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final SmsServiceClient client;

    @Override
    public boolean generateCode(String phone) {
        return client.generate(new Phone(phone)).getStatusCode().is2xxSuccessful();
    }

    @Override
    public boolean checkCode(SmsCode smsCode) {
        return client.check(smsCode).getStatusCode().is2xxSuccessful();
    }
}
