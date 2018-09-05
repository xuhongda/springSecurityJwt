package com.xu.security.springsecuritydemo.validate.sms;

/**
 * <p>
 *     提供接口以不同发送短信实现方式
 * </p>
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate.sms
 * springSecurityJwt
 */
public interface SmsCodeSender {
    /**
     * send
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
