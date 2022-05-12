package com.ms.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.reggie.mapper.ShoppingCartMapper;
import com.ms.reggie.pojo.ShoppingCart;
import com.ms.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author SerMs
 * @since 2022-05-11 22:39:37
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}

