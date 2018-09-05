package com.xu.security.springsecuritydemo.controller;


import com.xu.security.springsecuritydemo.pojo.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author xuhongda on 2018/9/4
 * com.xu.security.springsecuritydemo.controller
 * springSecurityJwt
 */
@RestController
public class UserController {
    @GetMapping("user")
    public List<User> user( User user){
        System.out.println(user);
        List<User> users = Arrays.asList(new User("xu", "18"), new User("yan", "18"));
        return users;
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
}
