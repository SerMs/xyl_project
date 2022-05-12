package com.ms.reggie.controller;

import com.ms.reggie.service.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单明细表(OrderDetail)表控制层
 *
 * @author SerMs
 * @since 2022-05-12 09:05:52
 */
@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {

    /**
     * 服务对象
     */
    @Resource
    private OrderDetailService orderDetailService;

}

