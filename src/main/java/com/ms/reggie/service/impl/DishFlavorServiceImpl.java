package com.ms.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.reggie.mapper.DishFlavorMapper;
import com.ms.reggie.pojo.DishFlavor;
import com.ms.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * 菜品口味关系表(DishFlavor)表服务实现类
 *
 * @author SerMs
 * @since 2022-05-08 16:22:40
 */
@Service("dishFlavorService")
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}

