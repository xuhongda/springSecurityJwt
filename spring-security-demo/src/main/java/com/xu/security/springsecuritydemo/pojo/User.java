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
public class User {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
