package com.xu.security.springsecuritydemo.properties;

import lombok.Data;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.properties
 * springSecurityJwt
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();


}