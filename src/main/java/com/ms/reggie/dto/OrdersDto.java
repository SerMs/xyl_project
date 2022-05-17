package com.ms.reggie.dto;

import com.ms.reggie.pojo.OrderDetail;
import com.ms.reggie.pojo.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

}
