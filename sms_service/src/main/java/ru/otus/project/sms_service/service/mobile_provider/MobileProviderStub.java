package ru.otus.project.sms_service.service.mobile_provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.project.sms_service.domain.SmsCode;

@Component
public class MobileProviderStub implements MobileProvider {
    private static final Logger logger = LoggerFactory.getLogger(MobileProviderStub.class);

    @Override
    // Emulating interaction with mobile provider
    public void send(SmsCode smsCode) {
        try {
            Thread.sleep(2500);

            logger.info("Sms '{}' was sent to {}", smsCode.getValue(), smsCode.getPhone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
