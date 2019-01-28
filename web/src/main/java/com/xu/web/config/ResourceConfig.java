package com.xu.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author xuhongda on 2018/10/23
 * com.xu.web.config
 * springSecurityJwt
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin()
        ;

    }

}
