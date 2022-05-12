package com.ms.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.reggie.mapper.OrderDetailMapper;
import com.ms.reggie.pojo.OrderDetail;
import com.ms.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * 订单明细表(OrderDetail)表服务实现类
 *
 * @author SerMs
 * @since 2022-05-12 09:05:52
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}

