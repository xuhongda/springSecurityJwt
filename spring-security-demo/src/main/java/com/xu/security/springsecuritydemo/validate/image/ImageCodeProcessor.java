package com.xu.security.springsecuritydemo.validate.image;

import com.xu.security.springsecuritydemo.validate.AbstractValidateCodeProcessor;
import com.xu.security.springsecuritydemo.validate.image.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.validate
 * springSecurityJwt
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}
