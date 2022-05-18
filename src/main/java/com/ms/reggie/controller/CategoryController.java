package com.ms.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.reggie.pojo.Category;
import com.ms.reggie.service.CategoryService;
import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品及套餐分类(Category)表控制层
 *
 * @author SerMs
 * @since 2022-05-07 21:52:40
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 分页查询所有数据
     */
    @GetMapping("/page")
    public R page(@RequestParam int page, @RequestParam int pageSize) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //创建排序规则
        queryWrapper.orderByAsc(Category::getSort);

        Page<Category> pages = new Page<>(page, pageSize);
        return R.success(this.categoryService.page(pages, queryWrapper));
    }

    /**
     * 新增数据
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("Category新增:{}", category);
        this.categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 单条/批量删除数据
     */
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("删除分类,id为:{}", ids);
        categoryService.remove(ids);
        return R.success("删除成功");
    }


    /**
     * 根据id修改分类信息
     *
     * @param category
     * @return
     */
    @PutMapping
    @CrossOrigin
    public R<String> putCategory(@RequestBody Category category) {
        log.info("修改分类信息:{}", category);
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    /**
     * 查询所有菜品分类
     *
     * @param category
     * @return
     */
    @GetMapping("/list")
    @CrossOrigin
    public R<List<Category>> listR(Category category) {

        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        //排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}

