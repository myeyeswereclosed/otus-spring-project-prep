package ru.otus.project.prep.service.sms;

import org.springframework.stereotype.Service;

@Service
public class SmsCodeChecker {
    public CheckerResult check(String phone) {
        return new CheckerResult(true, "Ok", phone);
    }
}
