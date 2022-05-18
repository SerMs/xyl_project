package com.ms.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.reggie.dto.OrdersDto;
import com.ms.reggie.pojo.Orders;
import com.ms.reggie.pojo.User;
import com.ms.reggie.service.OrdersService;
import com.ms.reggie.service.UserService;
import com.ms.reggie.util.BaseContext;
import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @CrossOrigin
    public R<String> list(@RequestBody Orders orders) {

        log.info("用户下单:{}", orders.toString());


        ordersService.submit(orders);
        return R.success("下单成功");
    }


    @Resource
    public UserService usersService;


    /**
     * 分页查询订单
     *
     * @return
     */
    @GetMapping("/userPage")
    @CrossOrigin
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


    /**
     * 后端分页
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    @CrossOrigin
    public R<Page> page(@Param("page") int page, @Param("pageSize") int pageSize, @Param("number") String number,
                        @Param("beginTime") String beginTime, @Param("endTime") String endTime) {

        log.info("订单分页,订单号:{},起始时间{},结束时间{}", number, beginTime, endTime);
        //分页对象
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        Page<OrdersDto> dtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        //根据订单号查询
        queryWrapper.like(number != null, Orders::getNumber, number)
                .between(beginTime != null, Orders::getOrderTime, beginTime, endTime);

        //添加排序条件,根据更新时间降序排列
        queryWrapper.orderByDesc(Orders::getOrderTime);
        ordersService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Orders> orders = pageInfo.getRecords();

        List<OrdersDto> list = orders.stream().map((item) -> {
            OrdersDto ordersDto = new OrdersDto();
            //对象拷贝
            BeanUtils.copyProperties(item, ordersDto);
            //用户id
            Long userId = item.getUserId();
            log.info("userid=={}", userId);
            //根据用户id查询用户姓名
            User user = usersService.getById(userId);
            log.info("user=={}", user.toString());
            ordersDto.setUserName(user.getName());
            return ordersDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }


    /**
     * 派送状态修改
     *
     * @param orders
     * @return
     */
    @PutMapping
    @CrossOrigin
    public R<String> status(@RequestBody Orders orders) {
        log.info("派送状态接收参数==status:{},id:=={}", orders.getStatus(), orders.getId());
        //根据id修改订单状态
        Orders ordersServiceById = ordersService.getById(orders.getId());
        ordersServiceById.setStatus(orders.getStatus());
        ordersService.updateById(ordersServiceById);
        return R.success("状态修改成功");
    }
}

