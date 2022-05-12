package com.ms.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.reggie.dto.DishDto;
import com.ms.reggie.mapper.DishMapper;
import com.ms.reggie.pojo.Dish;
import com.ms.reggie.pojo.DishFlavor;
import com.ms.reggie.pojo.SetmealDish;
import com.ms.reggie.service.DishFlavorService;
import com.ms.reggie.service.DishService;
import com.ms.reggie.service.SetmealDishService;
import com.ms.reggie.util.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理(Dish)表服务实现类
 *
 * @author SerMs
 * @since 2022-05-07 23:32:35
 */
@Service("dishService")
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Resource
    private DishFlavorService dishFlavorService;

    @Resource
    private SetmealDishService setmealDishService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品,同时保存对应的口味数据
     *
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        /* 保存菜品的基本信息到菜品表dish */
        this.save(dishDto);
        /* 获取菜品id赋值给口味表的菜品id字段 */
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        /* 保存菜品口味到口味表 dish_flavor */
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据id查询菜品信息和口味信息
     *
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息,从dish表查询
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        //查询当前菜品对应的口味信息,从dish_flavor
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> list = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(list);

        return dishDto;
    }


    /**
     * 跟新菜品信息,跟新对应的口味信息
     *
     * @param dishDto
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //跟新dish
        this.updateById(dishDto);
        //清理当前口味,delete dish_flavor
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //插入新的数据
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

    }

    /**
     * 删除菜品信息并删除对应口味信息
     *
     * @param ids
     */
    @Override
    public void deleteByIdWithFlavor(List<Long> ids) {
        //删除Redis缓存
        List<Dish> dishList = this.listByIds(ids);
        dishList.stream().map(dish -> "dish_" + dish.getCategoryId() + "_1").forEach(keys -> redisTemplate.delete(keys));
        for (Long id : ids) {
            LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
            long count = setmealDishService.count(setmealDishLambdaQueryWrapper.eq(SetmealDish::getDishId, id));
            if (count > 0) {
                //已经关联菜品,抛出异常
                throw new CustomException("当前菜品下关联了套餐,不能删除");
            }
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DishFlavor::getDishId, id);


            //根据菜品id删除对应的口味信息
            dishFlavorService.remove(queryWrapper);
            //删除对应id的菜品信息
            this.removeById(id);
        }
    }

}

