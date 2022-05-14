package com.ms.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.reggie.dto.SetmealDto;
import com.ms.reggie.pojo.Category;
import com.ms.reggie.pojo.Setmeal;
import com.ms.reggie.service.CategoryService;
import com.ms.reggie.service.SetmealService;
import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 套餐(Setmeal)表控制层
 *
 * @author SerMs
 * @since 2022-05-08 22:57:33
 */
@RestController
@Slf4j
@RequestMapping("setmeal")
public class SetmealController {

    @Resource
    private SetmealService setmealService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 新增套餐
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("套餐信息：{}", setmealDto);

        setmealService.saveWithDish(setmealDto);

        return R.success("新增套餐成功");
    }

    /**
     * 套餐分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        //分页构造器对象
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null, Setmeal::getName, name);
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //对象拷贝
            BeanUtils.copyProperties(item, setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * 删除套餐
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids:{}", ids);

        setmealService.removeWithDish(ids);

        return R.success("套餐数据删除成功");
    }

    /**
     * 根据条件查询套餐数据 前台/后台
     *
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        log.info("setmeal:{}", setmeal);
        List<Setmeal> list = null;

        //dish_123_1
        String key = "setmeal_" + setmeal.getCategoryId() + "_" + setmeal.getStatus();
        //先从缓存中获取到redis数据
        list = (List<Setmeal>) redisTemplate.opsForValue().get(key);
        //如果存在,直接返回,无需查询数据库
        if (list != null) {
            return R.success(list);
        }
        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(setmeal.getName()), Setmeal::getName, setmeal.getName());
        queryWrapper.eq(null != setmeal.getCategoryId(), Setmeal::getCategoryId, setmeal.getCategoryId());
        queryWrapper.eq(null != setmeal.getStatus(), Setmeal::getStatus, setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        list = setmealService.list(queryWrapper);

        //如果不存在,需要查询数据库,将查询到的菜品数据缓存到redis中
        redisTemplate.opsForValue().set(key, list, 60, TimeUnit.MINUTES);

        return R.success(list);
    }


    /**
     * 单条/批量/状态
     *
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> postDish(@PathVariable int status, @RequestParam List<Long> ids) {
        log.info("批修改状态status::{},ids::{}", status, ids);
        long id = Thread.currentThread().getId();
        log.info("2.SetmealController 线程id为:{}", id);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        List<Setmeal> list = setmealService.list(queryWrapper);

        //redis清理缓存
        for (Setmeal setmeal : list) {
            String keys = "setmeal_" + setmeal.getCategoryId() + "_1";
            redisTemplate.delete(keys);
        }


        List<Setmeal> list2 = list.stream().map((item) -> {
            item.setStatus(status);
            return item;
        }).collect(Collectors.toList());

        setmealService.updateBatchById(list2);

        return R.success("操作成功");
    }

    /**
     * 通过主键查询单条数据,用于数据回显
     */
    @GetMapping("{id}")
    public R<Setmeal> selectOne(@PathVariable Long id) {
        SetmealDto setmealDto = setmealService.getByIdWithDish(id);
        return R.success(setmealDto);
    }

    /**
     * 修改数据
     */
    @PutMapping
    public R<String> updateById(@RequestBody SetmealDto setmealDto) {
        setmealService.updateWithDish(setmealDto);
        String keys = "setmeal_" + setmealDto.getCategoryId() + "_1";
        redisTemplate.delete(keys);
        return R.success("套餐修改成功");
    }
}

