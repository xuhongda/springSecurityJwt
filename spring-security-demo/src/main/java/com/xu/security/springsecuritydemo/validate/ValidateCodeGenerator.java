package com.xu.security.springsecuritydemo.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate
 * springSecurityJwt
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);



}
