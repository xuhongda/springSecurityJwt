package com.xu.security.springsecuritydemo.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate
 * springSecurityJwt
 */
public class ValidateCodeException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
