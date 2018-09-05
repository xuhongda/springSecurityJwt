package com.xu.security.springsecuritydemo.validate.sms;


/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate.sms
 * springSecurityJwt
 */

public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
