package com.ms.reggie.controller;

import com.baomidou.kisso.captcha.ICaptcha;
import com.wf.captcha.SpecCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * </p>
 *
 * @author SerMs
 * @date 2022/6/21 23:29
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    @Resource
    private ICaptcha captcha;

    @RequestMapping("/hello")
    public void hello(HttpServletResponse response) throws IOException {
        // png类型
        SpecCaptcha captcha = new SpecCaptcha(130, 48);
        String text = captcha.text();// 获取验证码的字符
        char[] chars = captcha.textChar();// 获取验证码的字符数组

        System.out.println("验证码：" + text);
        System.out.println(chars);
        // 输出验证码
        captcha.out(response.getOutputStream());
    }


    // 生成验证，例如：http://localhost:8088/v1/captcha/image?ticket=123456
    @GetMapping("image")
    public void image(String ticket) {
        try {
            // 验证码信息存放在缓存中，key = ticket 、 value = 验证码文本内容
            captcha.generate(request, response.getOutputStream(), ticket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 校验图片验证码 123321
    @PostMapping("verification")
    public boolean verification(String ticket, String code) {
        // ticket 为生成验证码的票据， code 为图片验证码文本内容
        return captcha.verification(request, ticket, code);
    }
}

