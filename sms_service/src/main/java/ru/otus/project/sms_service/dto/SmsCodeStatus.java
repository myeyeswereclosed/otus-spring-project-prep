package ru.otus.project.sms_service.dto;

public enum SmsCodeStatus {
    SUCCESS, FAIL, EXPIRED;

    public boolean isOk() {
        return this.equals(SUCCESS);
    }
}
