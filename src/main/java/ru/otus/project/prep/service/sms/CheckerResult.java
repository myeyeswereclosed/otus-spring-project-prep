package ru.otus.project.prep.service.sms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class CheckerResult {
    private final boolean isOk;
    private final String message;
    private final String phone;
}
