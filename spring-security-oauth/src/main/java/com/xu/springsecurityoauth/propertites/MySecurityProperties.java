package com.xu.springsecurityoauth.propertites;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.propertites
 * springSecurityJwt
 */
@ConfigurationProperties(prefix = "com.xu")
@Data
@Component
public class MySecurityProperties {

    private MyOAuth2Properties myOAuth2Properties = new MyOAuth2Properties();

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();
}
