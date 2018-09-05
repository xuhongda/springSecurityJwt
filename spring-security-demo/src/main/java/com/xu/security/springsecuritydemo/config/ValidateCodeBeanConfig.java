package com.xu.security.springsecuritydemo.config;

import com.xu.security.springsecuritydemo.properties.SecurityProperties;
import com.xu.security.springsecuritydemo.validate.image.ImageCodeGenerator;
import com.xu.security.springsecuritydemo.validate.ValidateCodeGenerator;
import com.xu.security.springsecuritydemo.validate.sms.DefaultSmsCodeSender;
import com.xu.security.springsecuritydemo.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     图形验证码生成器可选配置
 * </p>
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.config
 * springSecurityJwt
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 可选验证码生成器，容器先找是否有 imageValidateCodeGenerator
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 短信验证
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
