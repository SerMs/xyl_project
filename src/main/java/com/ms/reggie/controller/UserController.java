package com.ms.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ms.reggie.pojo.User;
import com.ms.reggie.service.UserService;
import com.ms.reggie.util.R;
import com.ms.reggie.util.SendMessageUtil;
import com.ms.reggie.util.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户信息(User)表控制层
 *
 * @author SerMs
 * @since 2022-05-10 23:45:45
 */
@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/sendMsg")
    public R sendMsg(@RequestParam String key,
                     @RequestParam String phone,
                     @RequestParam String captcha, HttpSession session) {
        log.info("=====验证码获取==={}===={}===={}", key, captcha, phone);
        //从Redis获取缓存的图形验证码
        boolean msg = false;
        Object codeInSession = redisTemplate.opsForValue().get(key);
        if (codeInSession != null && codeInSession.equals(captcha)) {
            //删除redis
            //如果登录成功,就删除缓存的验证码
            redisTemplate.delete(key);
            log.info("删除验证码");
            msg = true;
        }
        if (msg) {
            //生成随机的四位随机数
            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            //调用短信服务
            Integer resultCode = SendMessageUtil.send(phone, "您正在登录湘约楼平台,请妥善保管您得验证码" + code);
            log.info("生成的验证码为:{},{}", code, SendMessageUtil.getMessage(resultCode));
            //需要将生成的验证码保存到Session
            log.info("验证码为:{}", code);

            //将验证码保存到session中
            session.setAttribute(phone, code);

            //将缓存的验证码保存到Redis中,并设置有效期五分钟
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

            log.info("短信发送成功~");
            return R.success("短信验证码发送成功");
        }
        return R.error("短信发送错误");
    }

    @PostMapping("/login")

    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info("map:{}", map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();

        //从Session中获取保存的验证码
//        Object codeInSession = session.getAttribute(phone);

        //从Redis获取缓存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        log.info("验证码为:{}", codeInSession);

        //验证码比对,页面提交的验证码和session中保存的验证码
        if (codeInSession != null && codeInSession.equals(code)) {
            //如果比对成功,说明登录成功
            //判断当前手机号是否为新用户,如果是新用户,即注册到数据库User表
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());

            //如果登录成功,就删除缓存的验证码
            redisTemplate.delete(phone);

            return R.success(user);
        }
        return R.error("登录失败");
    }


    @PostMapping("/loginout")
    public R<String> loginout(HttpSession session) {
        //清理Session中保存的当前登录员工的id
        session.removeAttribute("user");
        return R.success("退出成功");
    }
}

