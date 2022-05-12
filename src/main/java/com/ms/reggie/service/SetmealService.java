package com.ms.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.reggie.dto.SetmealDto;
import com.ms.reggie.pojo.Setmeal;

import java.util.List;

/**
 * 套餐(Setmeal)表服务接口
 *
 * @author SerMs
 * @since 2022-05-07 23:32:39
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐,同时需要保存套餐的关联关系,也就是套餐内包含的菜品
     *
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    void removeWithDish(List<Long> ids);


    SetmealDto getByIdWithDish(Long id);

    void updateWithDish(SetmealDto setmealDto);
}

