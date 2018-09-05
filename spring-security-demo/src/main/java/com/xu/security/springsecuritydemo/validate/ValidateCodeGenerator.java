package com.xu.security.springsecuritydemo.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>
 *     验证码生成器
 * </p>
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate
 * springSecurityJwt
 */
public interface ValidateCodeGenerator {
    /**
     * 验证码生成
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);

}
