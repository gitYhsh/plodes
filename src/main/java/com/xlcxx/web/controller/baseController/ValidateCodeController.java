package com.xlcxx.web.controller.baseController;


import com.xlcxx.config.auth.CodeFilter.img.ImageCode;
import com.xlcxx.config.auth.CodeFilter.sms.SmsCodeSender;
import com.xlcxx.config.auth.CodeFilter.utils.ValidateCode;
import com.xlcxx.config.auth.CodeFilter.utils.ValidateCodeGenerator;
import com.xlcxx.plodes.baseServices.RedisService;
import com.xlcxx.utils.ApiResult;
import com.xlcxx.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ValidateCodeController {

    private final static Logger logger = LoggerFactory.getLogger(ValidateCodeController.class);

    @Autowired
    private ValidateCodeGenerator ImageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator SmsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private RedisService redisService;


    @GetMapping("/image/code")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) ImageCodeGenerator.createCodeImage();
        BufferedImage image = imageCode.getImage();
        imageCode.setImage(null);

        String key = Constant.SESSION_KEY_IMAGE_CODE+imageCode.getCode();
        redisService.set(key,imageCode.getCode());
        redisService.pexpire(key,60000L);
        //sessionStrategy.setAttribute(new ServletWebRequest(request), Constant.SESSION_KEY_IMAGE_CODE, imageCode);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }

    @GetMapping("/sms/code")
    public ApiResult createSmsCode(HttpServletRequest request, HttpServletResponse response, String mobile) {
        try {
            ValidateCode smsCode = SmsCodeGenerator.createCodeSms();
            String key = Constant.SESSION_KEY_SMS_CODE+smsCode.getCode();
            Map<String,String> map = new HashMap<>();
            map.put("code",smsCode.getCode());
            map.put("mobile",mobile);
            redisService.haSet(key,map);
            redisService.pexpire(key,60000L);
            //弃用session//sessionStrategy.setAttribute(new ServletWebRequest(request), Constant.SESSION_KEY_SMS_CODE + mobile, smsCode);
            // 发送短信
            smsCodeSender.send(mobile, smsCode.getCode());
            return ApiResult.ok(smsCode.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("短信验证码发送失败", e);
            return ApiResult.error("");
        }
    }
    @GetMapping("/noAuthenticationFiter")
    public ApiResult login(HttpServletRequest request, HttpServletResponse response) {
        return ApiResult.error("尚未认证");
    }



}
