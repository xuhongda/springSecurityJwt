package com.xu.securityssoserver.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xuhongda on 2018/9/10
 * com.xu.securityssoserver.config
 * springSecurityJwt
 */
@Configuration
public class SsoWebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final Log logger = LogFactory.getLog(WebSecurityConfigurerAdapter.class);

    @Autowired
    private SsoUserDetailService ssoUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ssoUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("xxx");
        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
    }
}
