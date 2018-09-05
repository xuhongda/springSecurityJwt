package com.xu.security.springsecuritydemo.social.qq.api;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.social.qq.api
 * springSecurityJwt
 */
public interface QQ {
    /**
     * 获取 qq 用户信息
     * @return
     */
    QQUserInfo getUserInfo();
}
