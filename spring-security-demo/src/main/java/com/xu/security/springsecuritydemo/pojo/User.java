package com.xu.security.springsecuritydemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xuhongda on 2018/9/4
 * com.xu.security.springsecuritydemo.pojo
 * springSecurityJwt
 */

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String username;
    private String age;


}
