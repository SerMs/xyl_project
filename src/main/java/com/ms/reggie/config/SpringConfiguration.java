package com.ms.reggie.config;

import com.baomidou.kisso.captcha.ImageCaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * </p>
 *
 * @author SerMs
 * @date 2022/6/21 23:38
 */
@Configuration
public class SpringConfiguration {
    @Bean
    public ImageCaptcha imageCaptcha() {
        ImageCaptcha imageCaptcha = ImageCaptcha.getInstance();
        // 干扰量 1
        imageCaptcha.setInterfere(1);
        // 验证码内容长度 4 位
        imageCaptcha.setLength(4);
        // Gif 验证码
        // imageCaptcha.setGif(true);
        // 验证码存储处理类，默认存在在 session 实现类 CaptchaStoreSession 仅适用单机
        // 分布式可以采用 Redis 处理，例如 RedisCaptchaStore 实现 ICaptchaStore 接口
        // imageCaptcha.setCaptchaStore(new CaptchaStoreRedis());
        return imageCaptcha;
    }

}
