package com.ms.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.reggie.pojo.Category;

/**
 * 菜品及套餐分类(Category)表服务接口
 *
 * @author SerMs
 * @since 2022-05-07 21:52:40
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}

