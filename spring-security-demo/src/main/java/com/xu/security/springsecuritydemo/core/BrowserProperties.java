package com.xu.security.springsecuritydemo.core;

import com.xu.security.springsecuritydemo.properties.LoginResponseType;
import lombok.Data;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.core
 * springSecurityJwt
 */
@Data
public class BrowserProperties {
    /**
     * 指定默认页面
     */
    private String loginPage = "/login.html";

    /**
     * 指定默认返回类型
     */
    private LoginResponseType loginType = LoginResponseType.JSON;

}
