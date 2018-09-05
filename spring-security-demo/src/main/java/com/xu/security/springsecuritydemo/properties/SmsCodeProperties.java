package com.xu.security.springsecuritydemo.properties;

import lombok.Data;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.properties
 * springSecurityJwt
 */
@Data
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    /**
     * 需要验证码的url
     */
    private String url;

}
