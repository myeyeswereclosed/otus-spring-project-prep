package ru.otus.project.sms_service.service.code_generation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.sms_service.domain.SmsCode;
import ru.otus.project.sms_service.dto.SmsCodeStatus;
import ru.otus.project.sms_service.repository.SmsCodeRepository;

@Service
@RequiredArgsConstructor
public class SmsCodeService implements PhoneCodeService {
    private final SmsCodeRepository repository;
    private final PhoneCodeGenerator generator;

    @Transactional
    public SmsCode makeCode(String phone) {
        repository.invalidateActualFor(phone);

        return repository.save(generator.generate(phone));
    }

    @Transactional
    public SmsCodeStatus checkCode(SmsCode code) {
        var maybeCode = repository.findByPhoneAndValueAndActual(code.getPhone(), code.getValue(), true);

        maybeCode.ifPresent(smsCode -> repository.invalidate(smsCode.getId()));

        return
            maybeCode
                .map(codeFound -> codeFound.isExpired() ? SmsCodeStatus.EXPIRED : SmsCodeStatus.SUCCESS)
                .orElse(SmsCodeStatus.FAIL)
        ;
    }
}
