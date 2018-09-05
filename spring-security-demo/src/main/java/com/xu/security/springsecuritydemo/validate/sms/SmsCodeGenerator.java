package com.xu.security.springsecuritydemo.validate.sms;

import com.xu.security.springsecuritydemo.properties.SecurityProperties;
import com.xu.security.springsecuritydemo.validate.ValidateCode;
import com.xu.security.springsecuritydemo.validate.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>
 *     短信验证码生成器
 * </p>
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate.sms
 * springSecurityJwt
 */
@Component("smsValidateCodeGenerator")
@Data
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

}
