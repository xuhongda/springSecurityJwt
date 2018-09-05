package com.xu.security.springsecuritydemo.properties;

import lombok.Data;

/**
 * <p>
 *     图形验证码配置属性
 * </p>
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.properties
 * springSecurityJwt
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;

    private int height = 23;

}
