package com.ms.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.reggie.mapper.UserMapper;
import com.ms.reggie.pojo.User;
import com.ms.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息(User)表服务实现类
 *
 * @author SerMs
 * @since 2022-05-10 23:45:45
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

