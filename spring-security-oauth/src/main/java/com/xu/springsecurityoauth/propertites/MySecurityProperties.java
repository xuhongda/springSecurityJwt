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

    private MyOauth2Properties myOauth2Properties = new MyOauth2Properties();

}
