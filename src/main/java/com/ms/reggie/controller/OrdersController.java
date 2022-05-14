package com.ms.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.reggie.pojo.Orders;
import com.ms.reggie.service.OrdersService;
import com.ms.reggie.util.BaseContext;
import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单表(Orders)表控制层
 *
 * @author SerMs
 * @since 2022-05-12 09:05:33
 */
@RestController
@Slf4j
@RequestMapping("order")
public class OrdersController {

    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;


    @PostMapping("/submit")
    public R<String> list(@RequestBody Orders orders) {

        log.info("用户下单:{}", orders.toString());


        ordersService.submit(orders);
        return R.success("下单成功");
    }


    /**
     * 分页查询订单
     *
     * @return
     */
    @GetMapping("/userPage")
    public R userPage(@RequestParam int page, @RequestParam int pageSize) {

        //拿到当前登录的用户id
        Long userId = BaseContext.getCurrentId();
        log.info("用户订单分页查询获取用户id:{}", userId);

        //根据id查询用户的订单信息
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, userId);
        long count = ordersService.count(queryWrapper);

        if (count > 0) {
            queryWrapper.orderByDesc(Orders::getOrderTime);
            Page<Orders> pages = new Page<>(page, pageSize);
            return R.success(this.ordersService.page(pages, queryWrapper));
        }
        return R.success("没有用户信息");
    }

}

