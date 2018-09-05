package com.xu.security.springsecuritydemo.social.qq.connect;

import com.xu.security.springsecuritydemo.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.social.qq.connect
 * springSecurityJwt
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
