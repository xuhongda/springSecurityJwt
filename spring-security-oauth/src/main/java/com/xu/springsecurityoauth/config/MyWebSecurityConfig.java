package com.xu.springsecurityoauth.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Log logger = LogFactory.getLog(WebSecurityConfigurerAdapter.class);
    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    // @formatter:off
    protected void configure(HttpSecurity http) throws Exception {


        logger.debug("启用自己的WebSecurityConfigurerAdapter");

        http
                .authorizeRequests()
                .anyRequest().permitAll()       // 允许所有请求通过
                .and()
                .csrf()
                .disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement()                        // 定制我们自己的 session 策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session


    }
}
