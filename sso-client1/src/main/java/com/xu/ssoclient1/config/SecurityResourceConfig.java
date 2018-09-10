package com.xu.ssoclient1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author xuhongda on 2018/9/10
 * com.xu.securityssoserver.config
 * springSecurityJwt
 */
@Configuration
@EnableResourceServer
public class SecurityResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello", "/", "/getUser/")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
