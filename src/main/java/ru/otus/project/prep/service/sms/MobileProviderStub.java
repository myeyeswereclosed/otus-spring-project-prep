package ru.otus.project.prep.service.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.project.prep.domain.sms.SmsCode;

import java.util.Random;

@Component
public class MobileProviderStub implements MobileProvider {
    private static final Logger logger = LoggerFactory.getLogger(MobileProviderStub.class);

    @Override
    // Emulating interaction with mobile provider
    public void sendSms(SmsCode smsCode) {
        try {
            Thread.sleep(500);

            logger.info("Sms '{}' was sent to {}", smsCode.getValue(), smsCode.getPhone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
