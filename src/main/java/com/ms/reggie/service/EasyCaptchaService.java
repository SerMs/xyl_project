package com.ms.reggie.service;

import com.ms.reggie.enums.CodeTypeEnum;
import com.ms.reggie.util.EasyCaptchaProducer;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName EasyCaptchaService
 * @Author ChangLu
 * @Date 4/12/2022 6:20 PM
 * @Description easy-captcha业务工具类
 */
@Service
@Slf4j
public class EasyCaptchaService {

    @Resource
    private EasyCaptchaProducer easyCaptchaProducer;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取指定类型的验证码结果以及Base64编码值
     *
     * @param codeType 验证码类型
     * @return
     */
    public Map<String, String> getCaptchaValueAndBase64(CodeTypeEnum codeType) {
        Captcha captcha = null;
        if (codeType == null) {
            captcha = easyCaptchaProducer.getCaptcha();
        } else {
            captcha = easyCaptchaProducer.getCaptcha(codeType);
        }
        //1、获取到结果值
        String captchaValue = captcha.text().toLowerCase();
        log.info("===结果值:{}", captchaValue);
        //获取key
        String key = UUID.randomUUID().toString();
        //存入Redis
        String verifyKey = "captcha_codes" + key;
        log.info("====={}===={}", verifyKey, captchaValue);
        redisTemplate.opsForValue().set(verifyKey, captchaValue, 4, TimeUnit.MINUTES);
        //对于数学类型的需要进行处理
        if (codeType == null || codeType == CodeTypeEnum.ARITHMETIC) {
            if (captcha.getCharType() - 1 == CodeTypeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
                captchaValue = captchaValue.split("\\.")[0];
            }
        }
        //2、获取到Base64编码
        String captchaBase64 = captcha.toBase64();
        Map<String, String> result = new HashMap<>(2);
        result.put("key", verifyKey);         //生成的uuid
        result.put("base64", captchaBase64);    //生成的64格式验证码
        return result;

        /*
        *
        *   //从Redis获取缓存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);
 //如果登录成功,就删除缓存的验证码
            redisTemplate.delete(phone);

        * */
    }

}
