package com.xlcxx.config.auth.CodeFilter.sms;

public interface SmsCodeSender {
    void send(String mobile, String code);
}
