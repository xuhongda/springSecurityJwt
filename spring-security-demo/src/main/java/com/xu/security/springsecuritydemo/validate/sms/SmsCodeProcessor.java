package com.xu.security.springsecuritydemo.validate.sms;

import com.xu.security.springsecuritydemo.core.SecurityConstants;
import com.xu.security.springsecuritydemo.validate.AbstractValidateCodeProcessor;
import com.xu.security.springsecuritydemo.validate.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate.sms
 * springSecurityJwt
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
