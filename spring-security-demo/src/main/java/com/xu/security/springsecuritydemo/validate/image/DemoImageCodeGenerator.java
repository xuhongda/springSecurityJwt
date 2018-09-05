package com.xu.security.springsecuritydemo.validate.image;

import com.xu.security.springsecuritydemo.validate.ValidateCodeGenerator;
import com.xu.security.springsecuritydemo.validate.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate
 * springSecurityJwt
 */
/*@Component("imageValidateCodeGenerator")*/
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }

}
