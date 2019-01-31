package com.xu.springsecurityoauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author xuhongda on 2019/1/30
 * com.xu.springsecurityoauth.security
 * springSecurityJwt
 */
@Slf4j
@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/.well-known/jwks.json").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails build = User.builder()
                .username("admin")
                .password("123456")
                .roles("USER")
                .build();
        log.info("password = {}", build.getPassword());
        return new InMemoryUserDetailsManager(build);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
