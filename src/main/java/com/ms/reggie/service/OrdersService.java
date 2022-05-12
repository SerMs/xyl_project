package com.ms.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.reggie.pojo.Orders;

/**
 * 订单表(Orders)表服务接口
 *
 * @author SerMs
 * @since 2022-05-12 09:05:32
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 用户下单
     *
     * @param orders
     */
    public void submit(Orders orders);
}

