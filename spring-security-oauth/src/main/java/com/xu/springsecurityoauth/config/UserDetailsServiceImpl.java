package com.xu.springsecurityoauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xuhongda on 2019/1/30
 * com.xu.springsecurityoauth.security
 * springSecurityJwt
 */
@Slf4j
@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
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
     * <P>
     *     org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
     *     方法进行验证
     * </P>
     * @param id 一个代表身份的唯一ID
     * @return UserDetails
     * @throws UsernameNotFoundException ex
     */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        String name = "yan";
        if (name.equals(id)){
            log.info("yan come in...thanks you ... ");
            return User.builder().username("yan").password(passwordEncoder.encode("520")).roles("wife").build();
        }
        log.info("username = {}",id);
        //1. 按userName 查询 数据库找出密码
        String password = "123456";

        UserDetails build = User.builder()
                .username(id)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        log.info("password = {}", build.getPassword());
        return build;
    }
}
