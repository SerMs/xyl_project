package com.ms.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.reggie.dto.DishDto;
import com.ms.reggie.pojo.Dish;

import java.util.List;

/**
 * 菜品管理(Dish)表服务接口
 *
 * @author SerMs
 * @since 2022-05-07 23:32:35
 */
public interface DishService extends IService<Dish> {

    //新增菜品,同时插入菜品对应的口味数据,需要操作两张表: dish dish_flavor
    void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和口味信息
    DishDto getByIdWithFlavor(Long id);

    //跟新菜品信息,跟新对应的口味信息
    void updateWithFlavor(DishDto dishDto);

    //删除菜品信息并
    void deleteByIdWithFlavor(List<Long> ids);


}

