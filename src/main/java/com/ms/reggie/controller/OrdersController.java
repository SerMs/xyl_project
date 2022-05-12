package com.ms.reggie.controller;

import com.ms.reggie.pojo.Orders;
import com.ms.reggie.service.OrdersService;
import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

