package com.xu.springsecurityoauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author xuhongda on 2019/1/30
 * com.xu.springsecurityoauth.security
 * springSecurityJwt
 */
@Slf4j
@Configuration
public class UserConfig /*extends WebSecurityConfigurerAdapter*/ {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/.well-known/jwks.json").permitAll()
                .anyRequest().authenticated();
    }*/

    /**
     * 验证：关键在于注入一个 {@link UserDetailsService} Bean
     * @return {@link UserDetailsService}
     */
    @Bean
   /* @Override*/
    public UserDetailsService userDetailsService() {


        User.UserBuilder userBuilder = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"));




        UserDetails build = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();
        log.info("password = {}", build.getPassword());
        return new InMemoryUserDetailsManager(build);
    }

}
