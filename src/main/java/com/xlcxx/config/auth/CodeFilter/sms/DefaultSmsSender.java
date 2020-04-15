package com.xlcxx.config.auth.CodeFilter.sms;


public class DefaultSmsSender implements SmsCodeSender {

    private long expireIn = 120;

    @Override
    public void send(String mobile, String code) {
        System.out.println("手机号：" + mobile + "的短信验证码为：" + code + "，有效时间：" + expireIn + " 秒");
    }
}
