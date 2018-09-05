package com.xu.security.springsecuritydemo.social.qq.config;

import com.xu.security.springsecuritydemo.properties.QQProperties;
import com.xu.security.springsecuritydemo.properties.SecurityProperties;
import com.xu.security.springsecuritydemo.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.social.qq.config
 * springSecurityJwt
 */
@Configuration
@ConditionalOnProperty(prefix = "com.xu.security.social.qq",name = "app-id") //生效条件
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

}