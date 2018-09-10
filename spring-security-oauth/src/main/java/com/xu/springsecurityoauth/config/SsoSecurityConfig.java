package com.xu.springsecurityoauth.config;

import com.xu.springsecurityoauth.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xuhongda on 2018/9/10
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Configuration
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailService myUserDetailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SsoSecurityConfig(MyUserDetailService myUserDetailService, PasswordEncoder passwordEncoder) {
        this.myUserDetailService = myUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder);
    }
}
