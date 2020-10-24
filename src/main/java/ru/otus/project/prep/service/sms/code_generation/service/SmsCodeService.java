package ru.otus.project.prep.service.sms.code_generation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.prep.domain.sms.SmsCode;
import ru.otus.project.prep.repository.sms.SmsCodeRepository;

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
}
