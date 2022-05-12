package com.ms.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.reggie.mapper.CategoryMapper;
import com.ms.reggie.pojo.Category;
import com.ms.reggie.pojo.Dish;
import com.ms.reggie.pojo.Setmeal;
import com.ms.reggie.service.CategoryService;
import com.ms.reggie.service.DishService;
import com.ms.reggie.service.SetmealService;
import com.ms.reggie.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜品及套餐分类(Category)表服务实现类
 *
 * @author SerMs
 * @since 2022-05-07 21:52:40
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类,删除之前需要进行判断
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        long count = dishService.count(dishLambdaQueryWrapper);
        //查询当前分类是否关联了菜品,如果已经关联,抛出一个异常
        if (count > 0) {
            //已经关联菜品,抛出异常
            throw new CustomException("当前分类下关联了菜品,不能删除");
        }

        //查询当前分类是否关联了套餐,如果已经关联,抛出一个异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        long count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0) {
            //已经关联套餐,抛出异常
            throw new CustomException("当前分类下关联了套餐,不能删除");
        }
        //正常删除
        super.removeById(id);
    }

}

