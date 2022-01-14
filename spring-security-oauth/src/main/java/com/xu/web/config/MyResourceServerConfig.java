package com.xu.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 *
 * <p>
 *     Spring Security 中的ResourceServerConfigurerAdapter配置会
 *     覆盖WebSecurityConfigurerAdapter
 * </p>
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                //添加验证白名单
                .mvcMatchers("/login.html","/jquery-3.2.1.js","/index").permitAll()
                .antMatchers("/hello/**").hasRole("USER")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").failureForwardUrl("/index").permitAll()
        ;
    }

}