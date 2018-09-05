package com.xu.security.springsecuritydemo.config;

import com.xu.security.springsecuritydemo.controller.browser.MyAuthenctiationFailureHandler;
import com.xu.security.springsecuritydemo.controller.browser.MyAuthenticationSuccessHandler;
import com.xu.security.springsecuritydemo.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xuhongda on 2018/9/4
 * com.xu.security.config
 * springSecurityJwt
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    /**
     * 验证方式
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                //自定义登陆页面
                .loginPage("/myauthentication/require")
                //表单action
                .loginProcessingUrl("/myauthentication/form")
                //配置返回成功页
                .successHandler(myAuthenticationSuccessHandler)
                //配置返回失败页
                .failureHandler(myAuthenctiationFailureHandler)
                .and()
                .authorizeRequests()
                //匹配页面允许，避免不断重定向
                .antMatchers("/myauthentication/require",securityProperties.getBrowser().getLoginPage()
                        ,"/code/image").permitAll()
                //所有请求都要身份验证
                .anyRequest()
                .authenticated()
                .and()
                //csrf 问题
                .csrf()
                .disable();

    }

    /**
     * 密码加密
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
