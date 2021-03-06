package com.xu.security.springsecuritydemo.config;

import com.xu.security.springsecuritydemo.authentication.AbstractChannelSecurityConfig;
import com.xu.security.springsecuritydemo.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.xu.security.springsecuritydemo.core.SecurityConstants;
import com.xu.security.springsecuritydemo.returnHandler.MyAuthenctiationFailureHandler;
import com.xu.security.springsecuritydemo.returnHandler.MyAuthenticationSuccessHandler;
import com.xu.security.springsecuritydemo.properties.SecurityProperties;
import com.xu.security.springsecuritydemo.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * @author xuhongda on 2018/9/4
 * com.xu.security.config
 * springSecurityJwt
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 验证方式
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //验证配置
        applyPasswordAuthenticationConfig(http);

        http    .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                //验证码
                //.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).formLogin()
                //自定义登陆页面
                //.loginPage("/myauthentication/require")
                //表单action
               // .loginProcessingUrl("/myauthentication/form")
                //配置返回成功页
               // .successHandler(myAuthenticationSuccessHandler)
                //配置返回失败页
                //.failureHandler(myAuthenctiationFailureHandler)
              //  .and()
                //社交登录
                .apply(mySocialSecurityConfig)
                //配置记住我功能
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                //匹配页面允许，避免不断重定向；未登录授权
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
                ).permitAll()
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


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //第一次启动生成表
    	//tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}
