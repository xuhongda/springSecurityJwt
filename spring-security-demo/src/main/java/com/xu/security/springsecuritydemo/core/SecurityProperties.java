package com.xu.security.springsecuritydemo.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.core
 * springSecurityJwt
 */

@ConfigurationProperties(prefix = "com.xu")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
